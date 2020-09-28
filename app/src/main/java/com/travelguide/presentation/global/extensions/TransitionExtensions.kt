package com.travelguide.presentation.global.extensions

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.annotation.StringRes
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.ViewCompat

typealias AndroidPair<A, B> = androidx.core.util.Pair<A, B>

val View.transitionInfo: AndroidPair<View, String>
    get() {
        return AndroidPair(
            this,
            ViewCompat.getTransitionName(this)!!
        )
    }

fun Activity.createTransitionOptions(vararg views: View): Bundle? {
    val sharedViews = views.map { it.transitionInfo }.toTypedArray()
    return ActivityOptionsCompat.makeSceneTransitionAnimation(this, *sharedViews)
        .toBundle()
}

fun View.setTransitionName(@StringRes stringResId: Int, vararg args: Any) {
    transitionName = context.getString(stringResId, *args)
}