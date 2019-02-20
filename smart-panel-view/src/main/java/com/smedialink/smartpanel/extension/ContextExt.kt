package com.smedialink.smartpanel.extension

import android.content.Context
import android.content.res.Resources
import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import android.support.v4.content.ContextCompat
import android.util.DisplayMetrics

fun Context.color(@ColorRes colorId: Int) =
        ContextCompat.getColor(this, colorId)

fun Context.string(@StringRes resId: Int): String =
        resources.getString(resId)

fun Context.pxToDp(px: Int): Int =
        Math.round(px / (Resources.getSystem().displayMetrics.xdpi /
                DisplayMetrics.DENSITY_DEFAULT))
