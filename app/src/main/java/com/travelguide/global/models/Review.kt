package com.travelguide.global.models

data class Review(
    val text: String,
    val rating: Float,
    val authorName: String,
    val authorImageUrl: String
)