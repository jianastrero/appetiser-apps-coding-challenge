package com.jianastrero.appetiser_apps_coding_challenge.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.jianastrero.appetiser_apps_coding_challenge.binding.NonNullObservableField
import com.jianastrero.appetiser_apps_coding_challenge.models.Movie
import java.text.DecimalFormat

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    // Fields to show on the UI
    val title = NonNullObservableField("")
    val description = NonNullObservableField("")
    val priceHd = NonNullObservableField("")
    val priceHdRent = NonNullObservableField("")
    val price = NonNullObservableField("")
    val priceRent = NonNullObservableField("")
    val genre = NonNullObservableField("")
    val year = NonNullObservableField("")

    // Default currency format
    private val currencyFormat = DecimalFormat("#,##0.00")

    // When a movie is set, automatically set the values of the fields to be shown on th eUI
    var movie: Movie? = null
        set(value) {

            if (value != null) {
                currencyFormat.applyPattern("${value.currency} #,##0.00")
                title.set(value.trackName ?: "")
                description.set(value.longDescription ?: "")
                priceHd.set(currencyFormat.format(value.trackHdPrice))
                priceHdRent.set(currencyFormat.format(value.trackHdRentalPrice))
                price.set(currencyFormat.format(value.trackPrice))
                priceRent.set(currencyFormat.format(value.trackRentalPrice))
                genre.set(value.primaryGenreName ?: "")
                year.set(value.releaseDate?.split("-")?.get(0) ?: "")
            }

            field = value
        }
}