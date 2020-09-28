package com.travelguide.presentation.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.travelguide.R
import com.travelguide.global.models.Place
import com.travelguide.presentation.global.extensions.inflate
import com.travelguide.presentation.global.extensions.setTransitionName
import kotlinx.android.synthetic.main.item_popular_place.view.*

class PopularPlacesAdapter(
    private val places: List<Place>,
    private val onItemSelected: (Place) -> Unit,
    private val onAddToFavoritesClicked: (Place) -> Unit
) : RecyclerView.Adapter<PopularPlacesAdapter.ViewHolder>() {

    override fun getItemCount(): Int = places.size

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(parent.inflate(R.layout.item_popular_place))
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val place = places[position]
        val itemView = holder.itemView
        val placeId = place.id
        itemView.placeImageCard.setTransitionName(R.string.place_image_transition, placeId)
        itemView.placeTitle.setTransitionName(R.string.place_title_transition, placeId)
        itemView.placeDescription.setTransitionName(R.string.place_description_transition, placeId)
        itemView.placeInfoCard.setTransitionName(R.string.place_card_transition, placeId)

        itemView.placeTitle.text = place.name
        itemView.placeDescription.text = place.description

        itemView.addPlaceToFavorites.setOnClickListener {
            onAddToFavoritesClicked(place)
        }
        itemView.placeImageCard.setOnClickListener {
            onItemSelected(place)
        }
        itemView.placeInfoCard.setOnClickListener {
            onItemSelected(place)
        }

        Glide.with(itemView.context)
            .load(place.imageUrl)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(itemView.placeImage)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}