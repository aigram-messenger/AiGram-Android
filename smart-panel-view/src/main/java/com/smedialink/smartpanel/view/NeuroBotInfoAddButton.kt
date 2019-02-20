package com.smedialink.smartpanel.view

import android.content.Context
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.util.AttributeSet
import android.widget.Button
import android.widget.TextView
import com.smedialink.aigram.purchases.domain.model.ShopItem
import com.smedialink.smartpanel.R
import com.smedialink.smartpanel.extension.textColor

// Демонстрационная кнопочка для магазина
class NeuroBotInfoAddButton : Button {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    enum class State(@ColorRes val textColor: Int) {
        AVAILABLE_PAID(R.color.colorShopButtonTextEnable),
        INSTALLED_ENABLE(R.color.colorShopButtonTextEnable),
        INSTALLED_DISABLE(R.color.colorShopButtonTextDisable);
    }

    var state: ShopItem.ItemState = ShopItem.ItemState.Enabled
        set(value) {
            field = value
            val newState = resolveButtonState(value)

            text = newState.first
            textColor = newState.second.textColor
        }

    private fun resolveButtonState(shopItemState: ShopItem.ItemState): Pair<String, NeuroBotInfoAddButton.State> {
        return when (shopItemState) {
            ShopItem.ItemState.Installed -> Pair(context.getString(R.string.shop_button_label_disable), NeuroBotInfoAddButton.State.INSTALLED_DISABLE)
            is ShopItem.ItemState.Paid -> Pair(shopItemState.price, NeuroBotInfoAddButton.State.AVAILABLE_PAID)
            ShopItem.ItemState.Enabled -> Pair(context.getString(R.string.shop_button_label_enable), NeuroBotInfoAddButton.State.INSTALLED_ENABLE)
        }
    }

}

