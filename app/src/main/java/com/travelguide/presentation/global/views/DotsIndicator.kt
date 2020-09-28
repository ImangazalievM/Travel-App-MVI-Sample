package com.travelguide.presentation.global.views

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import com.travelguide.R
import com.travelguide.presentation.global.extensions.dpToPx

class DotsIndicator : LinearLayout {

    var selection: Int = 0
        private set
    private var dotsCount: Int = 2
    private var dotHeight: Int = dpToPx(7)
    private var dotWidth: Int = dpToPx(7)
    var marginsBetweenDots: Int = dpToPx(17)
    var selectedDotResource: Int = R.drawable.circle_white
    var unselectedDotResource: Int = R.drawable.circle_gray

    var onSelectListener: ((position: Int) -> Unit)? = null

    constructor(context: Context?) : super(context) {
        orientation = HORIZONTAL
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        gravity = Gravity.CENTER
    }

    constructor(context: Context?, attrs: AttributeSet) : super(context, attrs) {
        orientation = HORIZONTAL
        layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        gravity = Gravity.CENTER

        getContext().obtainStyledAttributes(
            attrs,
            R.styleable.DotsIndicator, 0, 0
        ).run {
            dotsCount = getInt(R.styleable.DotsIndicator_dots_count, 3)
            selectedDotResource = getResourceId(
                R.styleable.DotsIndicator_selected_dot_resource,
                selectedDotResource
            )
            unselectedDotResource = getResourceId(
                R.styleable.DotsIndicator_unselected_dot_resource,
                unselectedDotResource
            )

            dotHeight = getDimensionPixelSize(R.styleable.DotsIndicator_dot_height, dotHeight)
            dotWidth = getDimensionPixelSize(R.styleable.DotsIndicator_dot_width, dotWidth)
            marginsBetweenDots = getDimensionPixelSize(
                R.styleable.DotsIndicator_margins_between_dots,
                marginsBetweenDots
            )
            recycle()
        }


        initDots(dotsCount)
    }

    fun initDots(dotsCount: Int) {
        removeAllViews()
        for (i: Int in 0 until dotsCount) {
            val dot = ImageView(context)
            dot.id = i
            dot.tag = i
            dot.layoutParams = LayoutParams(dotHeight, dotWidth).apply {
                this.marginEnd = marginsBetweenDots
                this.gravity = Gravity.CENTER_VERTICAL
            }
            dot.scaleType = ImageView.ScaleType.FIT_XY

            if (selection == i) {
                dot.setImageResource(selectedDotResource)
            } else {
                dot.setImageResource(unselectedDotResource)
            }

            dot.setOnClickListener {
                onSelectListener?.invoke(it.tag as Int)
                setDotSelection(it.tag as Int)
            }
            addView(dot)
        }
        setDotSelection(selection)
    }

    fun setDotSelection(position: Int) {
        if (position == selection)
            return
        val newSelection: ImageView = findViewById(position)
        val selectedDot: ImageView = findViewWithTag(selection)

        newSelection.setImageResource(selectedDotResource)
        selectedDot.setImageResource(unselectedDotResource)
        selection = newSelection.tag as Int
    }

}