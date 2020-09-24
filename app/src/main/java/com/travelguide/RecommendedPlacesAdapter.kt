package com.travelguide

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.travelguide.global.models.Place
import kotlinx.android.synthetic.main.item_recommended_place.view.*

class RecommendedPlacesAdapter(
    private val places: List<Place>,
    private val onItemSelected: (Place) -> Unit
) : RecyclerView.Adapter<RecommendedPlacesAdapter.ViewHolder>() {

    override fun getItemCount(): Int = places.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_recommended_place, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val place = places[position]
        val itemView = holder.itemView
        itemView.placeTitle.text = place.name
        itemView.placeLocationDetails.text = place.description
        itemView.placeRatingBar.rating = place.rating
        itemView.placeRating.text = "%.1f".format(place.rating)

        itemView.setOnClickListener {
            onItemSelected(place)
        }

        Glide.with(itemView.context)
            .load(place.imageUrl)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(itemView.placeImageImage)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}