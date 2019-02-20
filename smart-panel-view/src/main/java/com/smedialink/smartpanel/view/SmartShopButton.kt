package com.smedialink.smartpanel.view

import android.content.Context
import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import android.util.AttributeSet
import android.widget.TextView
import com.smedialink.aigram.purchases.domain.model.ShopItem
import com.smedialink.smartpanel.R
import com.smedialink.smartpanel.extension.textColor

// Демонстрационная кнопочка для магазина
class SmartShopButton : TextView {

    init {
        isAllCaps = true
    }

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    enum class State(@ColorRes val textColor: Int, @DrawableRes val background: Int) {
        AVAILABLE_FREE(R.color.colorShopButtonTextLight, R.drawable.bg_shop_button_available_free),
        AVAILABLE_PAID(R.color.colorShopButtonTextLight, R.drawable.bg_shop_button_available_paid),
        INSTALLED_ENABLE(R.color.colorShopButtonTextEnable, R.drawable.bg_shop_button_installed_enable),
        INSTALLED_DISABLE(R.color.colorShopButtonTextDisable, R.drawable.bg_shop_button_installed_disable);
    }

    var state: ShopItem.ItemState = ShopItem.ItemState.Enabled
        set(value) {
            field = value
            val newState = resolveButtonState(value)

            text = newState.first
            textColor = newState.second.textColor
            setBackgroundResource(newState.second.background)
        }

    private fun resolveButtonState(shopItemState: ShopItem.ItemState): Pair<String, SmartShopButton.State> {
        return when (shopItemState) {
            ShopItem.ItemState.Installed -> Pair(context.getString(R.string.shop_button_label_disable), SmartShopButton.State.INSTALLED_DISABLE)
            is ShopItem.ItemState.Paid -> Pair(shopItemState.price, SmartShopButton.State.AVAILABLE_PAID)
            ShopItem.ItemState.Enabled -> Pair(context.getString(R.string.shop_button_label_enable), SmartShopButton.State.INSTALLED_ENABLE)
        }
    }

}

