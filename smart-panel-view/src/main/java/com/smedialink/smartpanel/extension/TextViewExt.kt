package com.smedialink.smartpanel.extension

import android.widget.TextView

var TextView.textColor: Int
    get() = currentTextColor
    set(v) = setTextColor(context.color(v))
