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

class MainViewModel(
    application: Application,
    private val iTunesSearchRepository: iTunesSearchRepository
) : AndroidViewModel(application) {

    // Fields to show on the UI
    val lastVisit = NonNullObservableField("")

    // Data
    val categorizedMovieList = mutableListOf<Pair<String, List<Movie>>>()
    var featured: Movie? = null

    /**
     * Reset the data
     */
    fun reset() {
        lastVisit.set(Settings.get(SETTINGS_LAST_VISIT, "Never"))
        categorizedMovieList.clear()
        featured = null
    }

    /**
     * Fetch data from the repository
     */
    fun fetchData() = CoroutineScope(Dispatchers.IO).launch {
        val search = iTunesSearchRepository.search()

        // sort the results and turn it to a mutable list
        val results =
            search.movies
                .sortedBy { it.trackName }
                .toMutableList()

        val mapped =
            results
                .distinctBy { it.primaryGenreName } // get 1 movie per genre
                .sortedBy { it.primaryGenreName } // sort remaining movies by genre
                .map {
                    (it.primaryGenreName ?: "") to
                        results
                            .filter { item ->
                                it.primaryGenreName == item.primaryGenreName
                            }
                            .toMutableList()
                            .sortedBy { item -> item.trackName }
                } // map the genre's to a list of movies on those genre

        // Clear current items on the list
        categorizedMovieList.clear()
        // Add a "Trending" category to add a little spice to the app
        categorizedMovieList.add("Trending" to results.random(10).toList())
        // Add all the mapped genre->movies
        categorizedMovieList.addAll(mapped)

        // get a random movie as a result
        featured = results.random()
    }
}