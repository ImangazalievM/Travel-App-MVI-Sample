package com.travelguide.presentation.global.views.roundedcornerslayout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.travelguide.R

class RoundCornerLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), CoordinatorLayout.AttachedBehavior {

    companion object {
        private const val CORNER_RADIUS_DEFAULT = 10.0f
    }

    private var topLeftRadius: Float = 0f
    private var topRightRadius: Float = 0f
    private var bottomLeftRadius: Float = 0f
    private var bottomRightRadius: Float = 0f
    private var radiusMultiplier: Float = 1f

    init {
        setupAttributes(attrs)
        setLayerType(View.LAYER_TYPE_SOFTWARE, null)
    }

    override fun getBehavior(): CoordinatorLayout.Behavior<*> {
        return RoundedLayoutBehavior(context)
    }

    override fun dispatchDraw(canvas: Canvas) {
        val count = canvas.save()
        val path = Path()
        val rect = RectF(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat())
        val arrayRadius = floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f)
        val topLeftRadius = topLeftRadius * radiusMultiplier
        arrayRadius[0] = topLeftRadius
        arrayRadius[1] = topLeftRadius
        val topRightRadius = topRightRadius * radiusMultiplier
        arrayRadius[2] = topRightRadius
        arrayRadius[3] = topRightRadius
        val bottomLeftRadius = bottomLeftRadius * radiusMultiplier
        arrayRadius[4] = bottomLeftRadius
        arrayRadius[5] = bottomLeftRadius
        val bottomRightRadius = bottomRightRadius * radiusMultiplier
        arrayRadius[6] = bottomRightRadius
        arrayRadius[7] = bottomRightRadius
        path.addRoundRect(rect, arrayRadius, Path.Direction.CW)
        canvas.clipPath(path)
        super.dispatchDraw(canvas)
        canvas.restoreToCount(count)
    }

    fun setCornerRadius(
        topLeft: Float? = null,
        topRight: Float? = null,
        bottomLeft: Float? = null,
        bottomRight: Float? = null
    ) {
        topLeft?.let { topLeftRadius = it  }
        topRight?.let { topRightRadius = it  }
        bottomLeft?.let { bottomLeftRadius = it  }
        bottomRight?.let { bottomRightRadius = it  }
        invalidate()
    }

    fun setCornersRadiusMultiplier(multiplier: Float) {
        radiusMultiplier = multiplier
        invalidate()
    }

    private fun setupAttributes(attrs: AttributeSet?) {
        val radius = getPixelValue(CORNER_RADIUS_DEFAULT)
        val a = context.obtainStyledAttributes(attrs, R.styleable.RoundedCornerLayout)
        if (a.hasValue(R.styleable.RoundedCornerLayout_topRadius)) {
            topLeftRadius = a.getDimension(R.styleable.RoundedCornerLayout_topRadius, 0f)
            topRightRadius = topLeftRadius
        } else {
            topLeftRadius = a.getDimension(R.styleable.RoundedCornerLayout_topLeftRadius, 0f)
            topRightRadius = a.getDimension(R.styleable.RoundedCornerLayout_topRightRadius, 0f)
        }
        if (a.hasValue(R.styleable.RoundedCornerLayout_bottomRadius)) {
            bottomLeftRadius = a.getDimension(R.styleable.RoundedCornerLayout_bottomRadius, 0f)
            bottomRightRadius = bottomLeftRadius
        } else {
            bottomLeftRadius =
                a.getDimension(R.styleable.RoundedCornerLayout_bottomLeftRadius, 0f)
            bottomRightRadius =
                a.getDimension(R.styleable.RoundedCornerLayout_bottomRightRadius, 0f)
        }
        a.recycle()
    }

    private fun getPixelValue(dip: Float): Float {
        val metrics = resources.displayMetrics
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, metrics)
    }

}