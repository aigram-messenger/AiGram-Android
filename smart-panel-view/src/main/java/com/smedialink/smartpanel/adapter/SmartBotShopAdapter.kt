package com.smedialink.smartpanel.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smedialink.aigram.purchases.domain.model.ShopItem
import com.smedialink.responses.domain.model.NeuroBotType
import com.smedialink.smartpanel.R
import kotlinx.android.synthetic.main.bots_shop_page_item.view.*

class SmartBotShopAdapter : RecyclerView.Adapter<SmartBotShopAdapter.ViewHolder>() {

    var onNeuroBotClickListener: (ShopItem) -> Unit = {}
    var onEnablButtonClickListener: ((ShopItem) -> Unit)? = null

    private val items = mutableListOf<ShopItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.bots_shop_page_item, parent, false)
        return ViewHolder(view, onEnablButtonClickListener, onNeuroBotClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(items[position])
    }

    override fun getItemCount(): Int =
            items.size

    override fun getItemId(position: Int): Long =
            position.toLong()

    fun setData(items: List<ShopItem>) {
        this.items.clear()
        this.items.addAll(items.sortedBy { it.smartBotType.position })
        notifyDataSetChanged()
    }

    class ViewHolder(
            itemView: View,
            private val buttonClickListener: ((ShopItem) -> Unit)?,
            private val botDescriptionListener: ((ShopItem) -> Unit)) : RecyclerView.ViewHolder(itemView) {

        fun bindTo(item: ShopItem) {
            with(itemView) {
                bot_name.text = context.getString(item.smartBotType.title)
                bot_description.text = context.getString(item.smartBotType.description)

                shop_button.state = item.state

                bot_avatar.setImageResource(item.avatar)

                bot_new_label.visibility =
                        if (NeuroBotType.isNew(item.smartBotType)) {
                            View.VISIBLE
                        } else {
                            View.GONE
                        }

                bot_avatar.setOnClickListener { botDescriptionListener.invoke(item) }
                bot_name.setOnClickListener { botDescriptionListener.invoke(item) }
                bot_type.setOnClickListener { botDescriptionListener.invoke(item) }
                bot_description.setOnClickListener { botDescriptionListener.invoke(item) }
                shop_button.setOnClickListener { buttonClickListener?.invoke(item) }
            }
        }
    }

}
