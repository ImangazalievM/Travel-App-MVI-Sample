package com.travelguide.global.models

data class Place(
    val name: String,
    val imageUrl: String,
    val description: String,
    val rating: Float,
    val reviewCount: Int
)