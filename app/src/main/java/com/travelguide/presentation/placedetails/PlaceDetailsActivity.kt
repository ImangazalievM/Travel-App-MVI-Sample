package com.travelguide.presentation.placedetails

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.doOnPreDraw
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.card.MaterialCardView
import com.travelguide.Constants
import com.travelguide.R
import com.travelguide.global.models.Place
import com.travelguide.presentation.global.extensions.getColorCompat
import com.travelguide.presentation.global.extensions.setTransitionName
import kotlinx.android.synthetic.main.activity_place_details.*
import kotlinx.android.synthetic.main.activity_place_details_content.*

class PlaceDetailsActivity : AppCompatActivity() {

    private val place = Place(
        id = 0,
        name = "Cappadocia",
        imageUrl = "${Constants.IMAGES_URL}/1.jpg",
        description = "Cappadocia isn’t like anywhere else in the world. With its unique rock formations, you’ll feel like you’ve landed on another planet. The only other places that have come close were Saxon Switzerland in Germany and the Valley of the Moon in Chile.",
        isInFavorites = true,
        rating = 3.2f,
        reviewCount = 84
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_details)

        initTransition()
        initHeader()
        showPlaceInfo()
    }

    private fun showPlaceInfo() {
        var isInFavorites = place.isInFavorites
        changeLikeIcon(isInFavorites)
        addFavoritesButton.setOnClickListener {
            isInFavorites = !isInFavorites
            changeLikeIcon(isInFavorites)
        }

        placeTitle.text = place.name
        placeDescription.text = place.description
        reviewsCountLabel.text = getString(R.string.reviews_count_label, place.reviewCount)

        placeRatingBar.rating = place.rating
        placeRating.text = place.ratingFormatted
    }

    private fun initHeader() {
        toolbar.setNavigationIcon(R.drawable.ic_arrow_white_24)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        toolbar.inflateMenu(R.menu.place_details_menu)

        val imagesAdapter = PlaceImagesPagerAdapter()
        placeImages.adapter = imagesAdapter
        imagesAdapter.setData(
            listOf(
                place.imageUrl,
                place.imageUrl,
                place.imageUrl,
                place.imageUrl
            )
        )

        imagesIndicator.initDots(imagesAdapter.itemCount)
        placeImages.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                imagesIndicator.setDotSelection(position)
            }
        })

        appBarLayout.addOnOffsetChangedListener(HeaderOnScrollHidingListener { showContent ->
            val scaleAndAlpha = if (showContent) 1f else 0f
            addFavoritesButton.animate().scaleY(scaleAndAlpha).scaleX(scaleAndAlpha).start()
            imagesIndicator.animate().alpha(scaleAndAlpha).start()
        })
    }

    private fun initTransition() {
        val placeId = 2
        placeTitle.setTransitionName(R.string.place_title_transition, placeId)
        placeDescription.setTransitionName(R.string.place_description_transition, placeId)
        placeImages.setTransitionName(R.string.place_image_transition, placeId)

        supportPostponeEnterTransition()
        (window.decorView as ViewGroup).doOnPreDraw {
            supportStartPostponedEnterTransition()
        }
    }

    private fun changeLikeIcon(isInFavorites: Boolean) {
        val iconResId = if (isInFavorites) {
            R.drawable.ic_favorite_filled
        } else {
            R.drawable.ic_favorite_outline
        }
        val color = if (isInFavorites) R.color.red_like else R.color.gray_500
        addFavoritesButton.setImageResource(iconResId)
        addFavoritesButton.drawable.setTint(getColorCompat(color))
    }

    override fun onBackPressed() {
        //super.onBackPressed()

        //addFavoritesButton.hide()
        //Log.d("Travel", "addFavoritesButton: " + addFavoritesButton.isShown)
        //addFavoritesButton.animate()
        //    .scaleX(0f)
        //    .scaleY(0f)
        //    .withEndAction { onBackPressed() }
        //    .start()
    }

}