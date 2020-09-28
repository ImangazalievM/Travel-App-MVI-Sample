package com.travelguide.presentation.placedetails

import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

class HeaderOnScrollHidingListener(
    private val onShowHideListener: (Boolean) -> Unit
) : AppBarLayout.OnOffsetChangedListener {

    companion object {
        private const val PERCENTAGE_TO_SHOW_CONTENT = 20
    }

    private var maxScrollSize = 0
    private var isContentShowing = true

    override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
        if (maxScrollSize == 0) maxScrollSize = appBarLayout.totalScrollRange

        val currentScrollPercentage: Int = (abs(verticalOffset) * 100 / maxScrollSize)

        if (currentScrollPercentage >= PERCENTAGE_TO_SHOW_CONTENT) {
            if (isContentShowing) {
                isContentShowing = false
                onShowHideListener(false)
            }
        }

        if (currentScrollPercentage < PERCENTAGE_TO_SHOW_CONTENT) {
            if (!isContentShowing) {
                isContentShowing = true
                onShowHideListener(true)
            }
        }
    }

}