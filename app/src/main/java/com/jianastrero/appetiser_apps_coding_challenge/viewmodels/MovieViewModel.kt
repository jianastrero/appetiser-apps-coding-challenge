package com.jianastrero.appetiser_apps_coding_challenge.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.jianastrero.appetiser_apps_coding_challenge.SETTINGS_LAST_VISIT
import com.jianastrero.appetiser_apps_coding_challenge.binding.NonNullObservableField
import com.jianastrero.appetiser_apps_coding_challenge.extensions.random
import com.jianastrero.appetiser_apps_coding_challenge.models.Movie
import com.jianastrero.appetiser_apps_coding_challenge.repositories.iTunesSearchRepository
import com.jianastrero.appetiser_apps_coding_challenge.singletons.Settings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class MovieViewModel(application: Application) : AndroidViewModel(application) {

    val title = NonNullObservableField("")
    val description = NonNullObservableField("")
    val priceHd = NonNullObservableField("")
    val priceHdRent = NonNullObservableField("")
    val price = NonNullObservableField("")
    val priceRent = NonNullObservableField("")

    private val currencyFormat = DecimalFormat("#,##0.00")

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
            }

            field = value
        }
}