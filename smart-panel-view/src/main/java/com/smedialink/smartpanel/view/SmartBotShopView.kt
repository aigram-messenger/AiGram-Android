package com.smedialink.smartpanel.view

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.FrameLayout
import com.smedialink.aigram.purchases.domain.GetShopItemsUseCase
import com.smedialink.aigram.purchases.domain.ManageShopItemUseCase
import com.smedialink.aigram.purchases.domain.model.ShopItem
import com.smedialink.aigram.purchases.domain.repository.ShopRepository
import com.smedialink.smartpanel.R
import com.smedialink.smartpanel.adapter.SmartBotShopAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.bots_shop_page.view.*

/**
 * Вью для отображения контента магазина ботов
 */
@SuppressLint("ViewConstructor")
class SmartBotShopView(
    context: Context,
    private val onNeuroBotClickListener: (ShopItem) -> Unit
) : FrameLayout(context) {

    private var adapter: SmartBotShopAdapter = SmartBotShopAdapter().apply { setHasStableIds(true) }
    private val repository: ShopRepository = ShopRepository(context)
    private val compositeDisposable = CompositeDisposable()
    private val manageShopItemUseCase = ManageShopItemUseCase(repository)
    private val getShopItemsUseCase = GetShopItemsUseCase(repository)

    init {
        adapter = SmartBotShopAdapter().apply { setHasStableIds(true) }

        View.inflate(context, R.layout.bots_shop_page, this)

        with(recycler) {
            adapter = this@SmartBotShopView.adapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        getShopItemsUseCase.getAll()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { applyState(State.LOADING) }
            .doOnNext { applyState(State.DATA) }
            .doOnError { applyState(State.ERROR) }
            .subscribe(adapter::setData, Throwable::printStackTrace)
            .let(compositeDisposable::add)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()

        adapter.onEnablButtonClickListener = { manageShopItemUseCase.manage(it, false) }
        adapter.onNeuroBotClickListener = onNeuroBotClickListener
    }

    fun dispose() {
        manageShopItemUseCase.dispose()
        getShopItemsUseCase.dispose()
        compositeDisposable.clear()
    }

    private fun applyState(state: State) {
        recycler.visibility = state.dataVisibility
        progressBar.visibility = state.loaderVisibility
        tvError.visibility = state.errorVisibility
    }

    enum class State(val loaderVisibility: Int,
                     val dataVisibility: Int,
                     val errorVisibility: Int) {
        LOADING(View.VISIBLE,
            View.INVISIBLE,
            View.INVISIBLE),
        DATA(View.INVISIBLE,
            View.VISIBLE,
            View.INVISIBLE),
        ERROR(View.INVISIBLE,
            View.INVISIBLE,
            View.VISIBLE)
    }
}
