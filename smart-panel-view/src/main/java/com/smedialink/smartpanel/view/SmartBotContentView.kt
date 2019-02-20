package com.smedialink.smartpanel.view

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.FrameLayout
import com.smedialink.smartpanel.R
import com.smedialink.smartpanel.SmartPanelView
import com.smedialink.smartpanel.adapter.SmartBotContentAdapter
import com.smedialink.smartpanel.model.TabContent
import com.smedialink.smartpanel.model.content.TabContentItem
import kotlinx.android.synthetic.main.bots_content_page.view.*

/**
 * Вью для отображения контента бота
 */
@SuppressLint("ViewConstructor")
class SmartBotContentView(
    context: Context,
    content: TabContent,
    listener: SmartPanelView.Listener?
) : FrameLayout(context) {

    private var adapter: SmartBotContentAdapter

    init {
        adapter = SmartBotContentAdapter().apply { setHasStableIds(true) }

        View.inflate(context, R.layout.bots_content_page, this)

        if (content is TabContentItem) {
            if(content.label != 0) {
                bot_name.text = context.getString(content.label)
            }

            bot_name.visibility =
                if (content.contentType == TabContent.Type.ADVERTISEMENT) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
        }

        with(recycler) {
            adapter = this@SmartBotContentView.adapter
            layoutManager = LinearLayoutManager(context)
        }

        adapter.setData(content)
        adapter.listener = listener
        adapter.notifyDataSetChanged()
    }
}
