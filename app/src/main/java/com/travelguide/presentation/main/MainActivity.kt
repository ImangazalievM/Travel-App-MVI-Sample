package com.travelguide.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.travelguide.Constants
import com.travelguide.R
import com.travelguide.global.models.Place
import com.travelguide.presentation.global.extensions.createTransitionOptions
import com.travelguide.presentation.global.extensions.getViewByPosition
import com.travelguide.presentation.placedetails.PlaceDetailsActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_popular_place.view.*
import kotlinx.android.synthetic.main.item_popular_place.view.placeTitle
import kotlinx.android.synthetic.main.item_recommended_place.view.*
import kotlinx.android.synthetic.main.item_popular_place.view.placeImage as popularPlaceImage

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val item = Place(
            id = 0,
            name = "Cappadocia",
            imageUrl = "${Constants.IMAGES_URL}/1.jpg",
            description = "Cappadocia isn’t like anywhere else in the world. With its unique rock formations, you’ll feel like you’ve landed on another planet. The only other places that have come close were Saxon Switzerland in Germany and the Valley of the Moon in Chile.",
            isInFavorites = true,
            rating = 4.7f,
            reviewCount = 84
        )
        val items = listOf(
            item.copy(id = 2, rating = 3.2f, imageUrl = "${Constants.IMAGES_URL}/1.jpg"),
            item.copy(id = 3, isInFavorites = false, rating = 4.3f, imageUrl = "${Constants.IMAGES_URL}/2.jpeg",),
            item.copy(id = 5, rating = 4.5f, imageUrl = "${Constants.IMAGES_URL}/3.jpg"),
            item.copy(id = 6, rating = 4.6f, imageUrl = "${Constants.IMAGES_URL}/4.jpg"),
            item.copy(id = 7, rating = 4.7f, imageUrl = "${Constants.IMAGES_URL}/5.jpg"),
            item.copy(id = 8, rating = 4.8f, imageUrl = "${Constants.IMAGES_URL}/6.jpg",),
            item.copy(id = 9, rating = 4.9f, imageUrl = "${Constants.IMAGES_URL}/2.jpeg"),
            item.copy(id = 10, rating = 5.0f, imageUrl = "${Constants.IMAGES_URL}/4.jpg",)
        )

        popularPlacesIndicator.removeAllTabs()
        (1..items.size).forEach {
            val tab = popularPlacesIndicator.newTab()
            tab.view.isEnabled = false
            popularPlacesIndicator.addTab(tab)
        }

        popularPlaces.adapter =
            PopularPlacesAdapter(
                places = items,
                onItemSelected = {
                    onPopularItemSelected(items, it)
                },
                onAddToFavoritesClicked = {

                }
            )
        popularPlaces.addOnItemChangedListener { _, position: Int ->
            popularPlacesIndicator.getTabAt(position)?.select()
        }

        recommendedPlaces.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recommendedPlaces.adapter = RecommendedPlacesAdapter(items) {
            onRecommendedItemSelected(items, it)
        }
    }

    private fun onPopularItemSelected(
        items: List<Place>,
        it: Place
    ) {
        val itemPosition = items.indexOf(it)
        val itemView = popularPlaces.getViewByPosition(itemPosition)!!
        val options = createTransitionOptions(
            itemView.placeTitle,
            itemView.placeDescription,
            itemView.placeImageCard,
            itemView.placeInfoCard
        )
        val intent = Intent(this, PlaceDetailsActivity::class.java)
        ActivityCompat.startActivity(this, intent, options)
    }


    private fun onRecommendedItemSelected(
        items: List<Place>,
        it: Place
    ) {
        val itemPosition = items.indexOf(it)
        val itemView = recommendedPlaces.getViewByPosition(itemPosition)!!
        val options = createTransitionOptions(
            itemView.placeTitle,
            itemView.placeImage,
            //itemView.placeCard,
            itemView.placeRatingBar,
            itemView.placeRating
        )
        val intent = Intent(this, PlaceDetailsActivity::class.java)
        ActivityCompat.startActivity(this, intent, options)
    }

}