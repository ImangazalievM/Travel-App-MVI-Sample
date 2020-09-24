package com.travelguide.data.places

import com.travelguide.global.models.Place
import com.travelguide.global.models.PlaceDetails

class PlacesRepository {

    suspend fun getPopularPlaces() : List<Place> {
        TODO()
    }

    fun getRecommendedPlaces() : List<Place> {
        TODO()
    }

    fun getPlaceDetails(placeId: Long) : PlaceDetails {
        TODO()
    }

}