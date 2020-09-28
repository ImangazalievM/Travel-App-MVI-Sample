package com.travelguide.presentation.global.views.roundedcornerslayout

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import com.travelguide.R
import com.travelguide.presentation.global.extensions.dpToPx

class RoundedLayoutBehavior @JvmOverloads constructor(
    private val context: Context,
    attrs: AttributeSet? = null
) : CoordinatorLayout.Behavior<RoundCornerLayout>(context, attrs) {

    private val toolbarHeightInPixel: Float by lazy {
        context.resources.getDimensionPixelSize(R.dimen.toolbar_height).toFloat()
    }

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: RoundCornerLayout,
        dependency: View
    ): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: RoundCornerLayout,
        dependency: View
    ): Boolean {
        val totalScrollRange = (dependency as AppBarLayout).totalScrollRange
        val radius = 1f - (-dependency.getY() / totalScrollRange)
        child.setCornersRadiusMultiplier(radius)
        if (totalScrollRange + dependency.getY() > child.measuredHeight.toFloat() / 2) {
            child.translationY =
                totalScrollRange + dependency.getY() + toolbarHeightInPixel - child.measuredHeight
                    .toFloat() / 2
        } else {
            child.translationY = toolbarHeightInPixel
        }
        return super.onDependentViewChanged(parent, child, dependency)
    }

}