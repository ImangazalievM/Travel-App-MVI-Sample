package com.travelguide.data.places

import com.travelguide.global.models.Place
import com.travelguide.global.models.DestinationDetails

class DestinationsRepository {

    suspend fun getPopularPlaces() : List<Place> {
        TODO()
    }

    fun getRecommendedPlaces() : List<Place> {
        TODO()
    }

    fun getPlaceDetails(placeId: Long) : DestinationDetails {
        TODO()
    }

}