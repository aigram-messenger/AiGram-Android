package com.smedialink.smartpanel.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smedialink.smartpanel.R
import com.smedialink.smartpanel.SmartPanelView
import com.smedialink.smartpanel.model.SmartPanelTab
import com.smedialink.smartpanel.model.TabContentItem
import com.smedialink.smartpanel.model.content.TabBotAnswerItem
import com.smedialink.smartpanel.model.content.TabBotNameItem
import com.smedialink.smartpanel.model.tabs.BotAnswersTab
import kotlinx.android.synthetic.main.bots_content_page_item_ads.view.*
import kotlinx.android.synthetic.main.bots_content_page_item_label.view.*
import kotlinx.android.synthetic.main.bots_content_page_item_normal.view.*

class SmartBotContentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var listener: SmartPanelView.Listener? = null

    private val items = mutableListOf<TabContentItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                TabContentItem.Type.ADVERT_BOT_ANSWER.value -> {
                    val view = LayoutInflater.from(parent.context)
                            .inflate(R.layout.bots_content_page_item_ads, parent, false)
                    AdsViewHolder(view)
                }
                TabContentItem.Type.NORMAL_BOT_ANSWER.value -> {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.bots_content_page_item_normal, parent, false)
                    NormalViewHolder(view)
                }
                else -> {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.bots_content_page_item_label, parent, false)
                    BotNameViewHolder(view)
                }
            }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AdsViewHolder -> holder.bindTo(items[position], position)
            is NormalViewHolder -> holder.bindTo(items[position], position)
            is BotNameViewHolder -> holder.bindTo(items[position])
        }
    }

    override fun getItemViewType(position: Int): Int =
        items[position].contentType.value

    override fun getItemCount(): Int =
        items.size

    override fun getItemId(position: Int): Long =
            position.toLong()

    fun setData(content: SmartPanelTab) {
        if (content is BotAnswersTab) {
            items.clear()
            items.addAll(content.answers)
        }
    }

    fun setData(answers: List<TabContentItem>) {
        items.clear()
        items.addAll(answers)
        notifyDataSetChanged()
    }

    inner class AdsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindTo(content: TabContentItem, position: Int) {
            content as TabBotAnswerItem
            itemView.ads_phrase.text = content.phrase
            itemView.setOnClickListener { listener?.onAnswerSelected(content, position) }
        }
    }

    inner class NormalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindTo(content: TabContentItem, position: Int) {
            content as TabBotAnswerItem
            itemView.normal_phrase.text = content.phrase
            itemView.setOnClickListener { listener?.onAnswerSelected(content, position) }
        }
    }

    inner class BotNameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindTo(content: TabContentItem) {
            content as TabBotNameItem
            itemView.bot_name.setText(content.nameResId)
        }
    }
}
