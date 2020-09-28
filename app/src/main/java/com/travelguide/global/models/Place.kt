package com.travelguide.global.models

data class Place(
    val id: Long,
    val name: String,
    val imageUrl: String,
    val description: String,
    val isInFavorites: Boolean,
    val rating: Float,
    val reviewCount: Int
) {

    val ratingFormatted = "%.1f".format(rating)

}