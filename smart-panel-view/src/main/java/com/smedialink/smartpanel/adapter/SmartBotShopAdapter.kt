package com.smedialink.smartpanel.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smedialink.responses.domain.model.enums.SmartBotType
import com.smedialink.smartpanel.R
import com.smedialink.smartpanel.model.content.TabShopItem
import kotlinx.android.synthetic.main.bots_shop_page_item.view.*

class SmartBotShopAdapter : RecyclerView.Adapter<SmartBotShopAdapter.ViewHolder>() {

    var onNeuroBotClickListener: (TabShopItem) -> Unit = {}
    var onEnableButtonClickListener: ((TabShopItem) -> Unit)? = null

    private val items = mutableListOf<TabShopItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.bots_shop_page_item, parent, false)
        return ViewHolder(view, onEnableButtonClickListener, onNeuroBotClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(items[position])
    }

    override fun getItemCount(): Int =
            items.size

    override fun getItemId(position: Int): Long =
            position.toLong()

    fun setData(items: List<TabShopItem>) {
        this.items.clear()
        this.items.addAll(items.sortedBy { it.item.smartBotType.position })
        notifyDataSetChanged()
    }

    class ViewHolder(
        itemView: View,
        private val buttonClickListener: ((TabShopItem) -> Unit)?,
        private val botDescriptionListener: ((TabShopItem) -> Unit)) : RecyclerView.ViewHolder(itemView) {

        fun bindTo(tabItem: TabShopItem) {
            with(itemView) {
                bot_name.text = context.getString(tabItem.item.smartBotType.title)
                bot_description.text = context.getString(tabItem.item.smartBotType.description)
                bot_type.setText(tabItem.item.smartBotType.contentType.label)

                shop_button.state = tabItem.item.state

                bot_avatar.setImageResource(tabItem.item.avatar)

                bot_new_label.visibility =
                    if (SmartBotType.isNew(tabItem.item.smartBotType)) {
                            View.VISIBLE
                        } else {
                            View.GONE
                        }

                bot_avatar.setOnClickListener { botDescriptionListener.invoke(tabItem) }
                bot_name.setOnClickListener { botDescriptionListener.invoke(tabItem) }
                bot_type.setOnClickListener { botDescriptionListener.invoke(tabItem) }
                bot_description.setOnClickListener { botDescriptionListener.invoke(tabItem) }
                shop_button.setOnClickListener { buttonClickListener?.invoke(tabItem) }
            }
        }
    }
}
