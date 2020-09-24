package com.travelguide

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.travelguide.global.models.Place
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val item = Place(
            name = "Hello",
            imageUrl = "${Constants.IMAGES_URL}/1.jpg",
            description = "Cappadocia isn’t like anywhere else in the world. With its unique rock formations, you’ll feel like you’ve landed on another planet. The only other places that have come close were Saxon Switzerland in Germany and the Valley of the Moon in Chile.",
            rating = 4.7f,
            reviewCount = 84
        )
        val items = listOf(
            item.copy(rating = 5.0f, imageUrl = "${Constants.IMAGES_URL}/1.jpg"),
            item.copy(rating = 4.0f, imageUrl = "${Constants.IMAGES_URL}/2.jpeg",),
            item.copy(rating = 4.9f, imageUrl = "${Constants.IMAGES_URL}/3.jpg"),
            item.copy(imageUrl = "${Constants.IMAGES_URL}/4.jpg"),
            item.copy(rating = 4.1f, imageUrl = "${Constants.IMAGES_URL}/5.jpg"),
            item.copy(imageUrl = "${Constants.IMAGES_URL}/6.jpg",),
            item.copy(rating = 4.3f, imageUrl = "${Constants.IMAGES_URL}/2.jpeg"),
            item.copy(imageUrl = "${Constants.IMAGES_URL}/4.jpg",)
        )

        popularPlacesIndicator.removeAllTabs()
        (1..items.size).forEach {
            val tab = popularPlacesIndicator.newTab()
            tab.view.isEnabled = false
            popularPlacesIndicator.addTab(tab)
        }

        popularPlaces.adapter = PopularPlacesAdapter(items) {

        }
        popularPlaces.addOnItemChangedListener { _, position: Int ->
            popularPlacesIndicator.getTabAt(position)?.select()
        }

        recommendedPlaces.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recommendedPlaces.adapter = RecommendedPlacesAdapter(items) {

        }
    }

}