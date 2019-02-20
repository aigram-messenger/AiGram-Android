package com.smedialink.smartpanel.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.smedialink.smartpanel.R

import kotlinx.android.synthetic.main.bots_popup.view.*

@SuppressLint("ViewConstructor")
class NeuroBotPopupView(context: Context, private val delegate: NeuroBotPopupViewDelegate) : FrameLayout(context) {

    interface NeuroBotPopupViewDelegate {
        fun onInfoClick()
        fun onShareClick()
        fun onDisableClick()
    }

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.bots_popup, this, true)

        setClickListener(bot_info_img) { delegate.onInfoClick() }
        setClickListener(bot_info_text) { delegate.onInfoClick() }
        setClickListener(bot_share_img) { delegate.onShareClick() }
        setClickListener(bot_share_text) { delegate.onShareClick() }
        setClickListener(bot_disable_img) { delegate.onDisableClick() }
        setClickListener(bot_disable_text) { delegate.onDisableClick() }
    }

    private fun setClickListener(view: View, callback: () -> Unit) {
        view.setOnClickListener { callback.invoke() }
    }
}