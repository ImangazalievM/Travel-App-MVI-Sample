package com.travelguide.presentation.global.extensions

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat

fun Context.getColorCompat(
    @ColorRes colorResId: Int
) : Int {
    return ContextCompat.getColor(this, colorResId)
}

fun Context.getTintedDrawable(
    @DrawableRes drawableResId: Int,
    color: Int
) : Drawable {
    val drawable = AppCompatResources.getDrawable(this, drawableResId)!!
    drawable.setTint(color)
    return drawable
}

fun Drawable.setTint(color: Int) {
    val wrappedDrawable = DrawableCompat.wrap(this)
    DrawableCompat.setTint(wrappedDrawable, color)
}