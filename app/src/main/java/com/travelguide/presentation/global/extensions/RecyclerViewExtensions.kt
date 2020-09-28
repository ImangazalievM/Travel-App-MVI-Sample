package com.travelguide.presentation.global.extensions

import android.view.View
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.getViewByPosition(position: Int): View? {
    return findViewHolderForAdapterPosition(position)?.itemView
}