package com.travelguide.presentation.placedetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.travelguide.R
import com.travelguide.presentation.global.extensions.inflate
import kotlinx.android.synthetic.main.item_place_image.view.*

class PlaceImagesPagerAdapter : RecyclerView.Adapter<PlaceImagesPagerAdapter.ViewHolder>() {

    private var images: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.item_place_image))

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemView = holder.itemView

        Glide.with(itemView.context)
            .load(images[position])
            .into(itemView.image)
    }

    fun setData(images: List<String>) {
        this.images = images
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}