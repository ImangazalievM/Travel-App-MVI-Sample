package com.travelguide.presentation.global.views

import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.widget.Checkable
import androidx.appcompat.widget.AppCompatImageButton
import androidx.core.view.accessibility.AccessibilityEventCompat

class CheckableImageButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int = 0
) : AppCompatImageButton(context, attrs, defStyleAttr), Checkable {

    private var isChecked: Boolean = false

    override fun setChecked(checked: Boolean) {
        if (this.isChecked != checked) {
            this.isChecked = checked
            refreshDrawableState()
            sendAccessibilityEvent(AccessibilityEventCompat.TYPE_WINDOW_CONTENT_CHANGED)
        }
    }

    override fun isChecked(): Boolean {
        return isChecked
    }

    override fun toggle() {
        isChecked = !this.isChecked
    }

    override fun performClick(): Boolean {
        toggle()
        return super.performClick()
    }

    override fun onCreateDrawableState(extraSpace: Int): IntArray {
        return if (this.isChecked) mergeDrawableStates(
            super.onCreateDrawableState(extraSpace + DRAWABLE_STATE_CHECKED.size),
            DRAWABLE_STATE_CHECKED
        ) else super.onCreateDrawableState(extraSpace)
    }

    override fun onSaveInstanceState(): Parcelable {
        val result = SavedState(super.onSaveInstanceState())
        result.checked = this.isChecked
        return result
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        if (state !is SavedState) {
            super.onRestoreInstanceState(state)
            return
        }
        super.onRestoreInstanceState(state.superState)
        isChecked = state.checked
    }

    private class SavedState : BaseSavedState {

        var checked = false

        internal constructor(superState: Parcelable?) : super(superState) {}

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(if (checked) 1 else 0)
        }

        private constructor(input: Parcel) : super(input) {
            checked = input.readInt() === 1
        }

        companion object {
            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(input: Parcel): SavedState {
                    return SavedState(input)
                }

                override fun newArray(size: Int): Array<SavedState?> {
                    return arrayOfNulls(size)
                }
            }
        }
    }

    companion object {
        private val DRAWABLE_STATE_CHECKED = intArrayOf(android.R.attr.state_checked)
    }

}