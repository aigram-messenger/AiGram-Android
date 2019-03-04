package com.smedialink.smartpanel

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.smedialink.responses.data.repository.AnswersStorageRepository
import com.smedialink.responses.data.repository.SmartBotRepository
import com.smedialink.responses.domain.model.enums.SmartBotContentType
import com.smedialink.responses.domain.model.enums.SmartBotType
import com.smedialink.responses.domain.model.response.SmartBotResponse
import com.smedialink.responses.event.SmartBotEventBus
import com.smedialink.smartpanel.extension.floatToDp
import com.smedialink.smartpanel.mapper.SmartContentMapper
import com.smedialink.smartpanel.model.SmartPanelTab
import com.smedialink.smartpanel.model.content.TabBotAnswerItem
import com.smedialink.smartpanel.model.content.TabShopItem
import com.smedialink.smartpanel.model.tabs.BotAnswersTab
import com.smedialink.smartpanel.view.SmartBotContentView
import com.smedialink.smartpanel.view.SmartBotShopView
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.panel_view.view.*
import org.jetbrains.annotations.NotNull

@SuppressLint("ViewConstructor")
class SmartPanelView(
    context: Context,
    private var showBotInfoClickListener: ShowBotInfoClickListener,
    private var showBotPopupClickListener: ShowBotPopupClickListener
) : FrameLayout(context), ViewPager.OnPageChangeListener {

    interface Listener {
        fun onAnswerSelected(@NotNull answer: TabBotAnswerItem, position: Int)
        fun onSmartPanelTab(isExpandable: Boolean)
        fun onBotsRefreshed()
        fun onBotInstalled(type: SmartBotType)
    }

    interface ShowBotInfoClickListener {
        fun onShowBotInfoClick(shopItem: TabShopItem)
    }

    interface ShowBotPopupClickListener {
        fun onShowBotPopupClick(botType: SmartBotType)
    }

    private var content: MutableList<SmartPanelTab> = mutableListOf()

    private val mapper: SmartContentMapper = SmartContentMapper()
    private val disposables: CompositeDisposable = CompositeDisposable()
    private val botEvents = SmartBotEventBus.get()
    private val botsRepository = SmartBotRepository.getInstance(context)
    private val answersRepository = AnswersStorageRepository.getInstance(context)
    private var listener: SmartPanelView.Listener? = null
    private var shopView: SmartBotShopView? = null
    private var panelHeight: Int = 0
    private var shouldUpdatePages = true

    init {
        View.inflate(context, R.layout.panel_view, this)
        viewpager.addOnPageChangeListener(this)
        tabs.setupWithViewPager(viewpager)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        listener?.onSmartPanelTab(isExpandable = isCurrentTabExpandable(position))
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val height = if (panelHeight != 0) panelHeight else layoutParams.height

        super.onMeasure(
            View.MeasureSpec.makeMeasureSpec(layoutParams.width, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY))
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(true)
        }
        return super.onInterceptTouchEvent(ev)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        botsRepository.cacheNewBotsIfAvailable()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(::listenForEvents, Throwable::printStackTrace)
            .let(disposables::add)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        disposables.clear()
        shopView?.dispose()
    }

    /**
     * Служебный метод для управления высотой панели
     */
    fun setHeight(newHeight: Int) {
        panelHeight = newHeight
        this.layoutParams.height = newHeight
    }

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    fun setData(list: List<SmartBotResponse>?) {
        content.clear()
        content.addAll(mapper.mapToTabs(list))

        viewpager.adapter = pagerAdapter
        pagerAdapter.notifyDataSetChanged()

        updateIcons()
        updateTabListeners()
        wrapTabIndicatorToTitle()
        moveToInitialPosition()
        firePageSelectedEvent()
    }

    private fun listenForEvents() {
        botEvents.subscribe({ event: SmartBotEventBus.Event ->
            when (event) {
                is SmartBotEventBus.Event.BotsRefreshed -> {
                    shouldUpdatePages = event.refreshPanel
                    listener?.onBotsRefreshed()
                }
                is SmartBotEventBus.Event.BotInstalled -> {
                    listener?.onBotInstalled(event.type)
                }
                is SmartBotEventBus.Event.BotAnswerChosen -> {
                    handleChosenAnswer(event)
                    SmartBotEventBus.flush()
                }
            }
        }, { e: Throwable ->
            e.printStackTrace()
        })
            .also { disposables.add(it) }
    }

    private fun handleChosenAnswer(event: SmartBotEventBus.Event.BotAnswerChosen) {
        Completable.fromAction {
            if (event.botType == SmartBotType.HOLIDAYS) {
                answersRepository.saveHolidayGreeting(event.userId, event.tag)
            }
            answersRepository.saveResponseCounter(event.botType, event.tag, event.position)
        }
        .subscribeOn(Schedulers.io())
        .subscribe({}, { e -> e.printStackTrace() })
        .also { disposables.add(it) }
    }

    private fun updateIcons() {
        content.forEachIndexed { index, item ->
            tabs.getTabAt(index)?.setIcon(item.icon)
        }
    }

    private fun updateTabListeners() {
        val tabStrip = tabs.getChildAt(0)
        if (tabStrip is ViewGroup) {
            val childCount = tabStrip.childCount
            for (i in 0 until childCount) {
                tabStrip.getChildAt(i).setOnLongClickListener { v ->
                    val position = tabStrip.indexOfChild(v)
                    val item = content[position]
                    if (item is BotAnswersTab &&
                        (item.botType?.contentType == SmartBotContentType.NEURO ||
                            item.botType?.contentType == SmartBotContentType.NORMAL)) {
                        item.botType?.let { showBotPopupClickListener.onShowBotPopupClick(it) }
                        true
                    } else {
                        false
                    }
                }
            }
        }
    }

    // Подгонка ширины табов под иконки
    private fun wrapTabIndicatorToTitle() {
        val padding = floatToDp(14f)
        val tabStrip = tabs.getChildAt(0)
        if (tabStrip is ViewGroup) {
            val childCount = tabStrip.childCount
            for (i in 0 until childCount) {
                val tabView = tabStrip.getChildAt(i)
                tabView.minimumWidth = 0
                tabView.setPadding(padding, tabView.paddingTop, padding, tabView.paddingBottom)
                if (tabView.layoutParams is ViewGroup.MarginLayoutParams) {
                    val layoutParams = tabView.layoutParams as ViewGroup.MarginLayoutParams
                    setTabMargin(layoutParams, 0, 0)
                }
            }

            tabs.requestLayout()
        }
    }

    private fun setTabMargin(layoutParams: ViewGroup.MarginLayoutParams, start: Int, end: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            layoutParams.marginStart = start
            layoutParams.marginEnd = end
            layoutParams.leftMargin = start
            layoutParams.rightMargin = end
        } else {
            layoutParams.leftMargin = start
            layoutParams.rightMargin = end
        }
    }

    /**
     * Выбор текущей активной вкладки.
     * Условия:
     * - магазин всегда в нулевой вкладке
     * - если сработали рекламные боты, то они всегда в первой вкладке
     * - если есть только нормальные боты, текущей становится первая вкладка нормального бота (1)
     * - если есть только реклама, текущей становится вкладка рекламного бота (1)
     * - если есть реклама и нормальные боты, текущей становится первая вкладка нормального бота (2)
     */
    private fun moveToInitialPosition() {
        val advertIndex =
            content.indexOfFirst { it.type == SmartPanelTab.Type.ADVERTISEMENT }

        if (!shouldUpdatePages) {
            shouldUpdatePages = true
            return
        }

        when {
        // Рекламы нет
            advertIndex == -1 -> {
                viewpager.setCurrentItem(1, true)
            }
        // Только реклама
            advertIndex == content.lastIndex -> {
                viewpager.setCurrentItem(advertIndex, true)
            }
        // Только магазин
            content.size == 1 -> {
                viewpager.setCurrentItem(0, true)
            }
        // Реклама + боты
            else -> {
                viewpager.setCurrentItem(advertIndex + 1, true)
            }
        }
    }

    private fun firePageSelectedEvent() {
        viewpager.post { onPageSelected(viewpager.currentItem) }
    }

    /**
     * Проверка должна ли текущая вкладка быть раскрываемой на весь экран,
     * пока к таким относится только вкладка магазина ботов
     */
    private fun isCurrentTabExpandable(position: Int): Boolean =
        content[position].type == SmartPanelTab.Type.SHOP

    private val pagerAdapter = object : PagerAdapter() {

        override fun isViewFromObject(view: View, obj: Any): Boolean =
            view == obj

        override fun getCount(): Int =
            content.size

        override fun instantiateItem(container: ViewGroup, position: Int): Any {
            val contentItem = content[position]
            val view: ViewGroup
            when (contentItem.type) {
                SmartPanelTab.Type.SHOP -> {
                        view = SmartBotShopView(container.context, showBotInfoClickListener::onShowBotInfoClick)
                        shopView = view
                    }
                    else -> {
                        view = SmartBotContentView(container.context, contentItem, listener)
                    }
                }
            container.addView(view)
            return view
        }

        override fun getPageTitle(position: Int): CharSequence? = null

        override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
            container.removeView(obj as View)
        }
    }
}
