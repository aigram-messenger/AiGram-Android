package com.smedialink.smartpanel.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smedialink.smartpanel.R
import com.smedialink.smartpanel.SmartPanelView
import com.smedialink.smartpanel.model.TabContent
import com.smedialink.smartpanel.model.content.AnswerItem
import com.smedialink.smartpanel.model.content.TabContentItem
import kotlinx.android.synthetic.main.bots_content_page_item_ads.view.*
import kotlinx.android.synthetic.main.bots_content_page_item_normal.view.*

class SmartBotContentAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var listener: SmartPanelView.Listener? = null

    private val items = mutableListOf<AnswerItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
            when (viewType) {
                TabContent.Type.ADVERTISEMENT.value -> {
                    val view = LayoutInflater.from(parent.context)
                            .inflate(R.layout.bots_content_page_item_ads, parent, false)
                    AdsViewHolder(view)
                }
                else -> {
                    val view = LayoutInflater.from(parent.context)
                            .inflate(R.layout.bots_content_page_item_normal, parent, false)
                    NormalViewHolder(view)
                }
            }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is AdsViewHolder -> holder.bindTo(items[position], position)
            is NormalViewHolder -> holder.bindTo(items[position], position)
        }
    }

    override fun getItemViewType(position: Int): Int =
            if (items[position].isAdvertising) {
                TabContent.Type.ADVERTISEMENT.value
            } else {
                TabContent.Type.NORMAL_BOT.value
            }

    override fun getItemCount(): Int =
            items.size

    override fun getItemId(position: Int): Long =
            position.toLong()

    fun setData(content: TabContent) {
        if (content is TabContentItem) {
            items.clear()
            items.addAll(content.answers)
        }
    }

    fun setData(answers: List<AnswerItem>) {
        items.clear()
        items.addAll(answers)
        notifyDataSetChanged()
    }

    inner class AdsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindTo(content: AnswerItem, position: Int) {
            with(itemView) {
                ads_phrase.text = content.phrase
                setOnClickListener { listener?.onAnswerSelected(content.phrase, content.link, position) }
            }
        }
    }

    inner class NormalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindTo(content: AnswerItem, position: Int) {
            with(itemView) {
                normal_phrase.text = content.phrase
                setOnClickListener { listener?.onAnswerSelected(content.phrase, "", position) }
            }
        }
    }
}
