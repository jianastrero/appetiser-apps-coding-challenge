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

    val lastVisit = NonNullObservableField("")

    val categorizedMovieList = mutableListOf<Pair<String, List<Movie>>>()
    var featured: Movie? = null

    fun reset() {
        lastVisit.set(Settings.get(SETTINGS_LAST_VISIT, "Never"))
        categorizedMovieList.clear()
        featured = null
    }

    fun fetchData() = CoroutineScope(Dispatchers.IO).launch {
        val search = iTunesSearchRepository.search()

        val results =
            search.movies
                .sortedBy { it.trackName }
                .toMutableList()

        val mapped =
            results
                .distinctBy { it.primaryGenreName }
                .sortedBy { it.primaryGenreName }
                .map {
                    (it.primaryGenreName ?: "") to
                        results
                            .filter { item ->
                                it.primaryGenreName == item.primaryGenreName
                            }
                            .toMutableList()
                            .sortedBy { item -> item.trackName }
                }

        categorizedMovieList.clear()
        categorizedMovieList.add("Trending" to results.random(10).toList())
        categorizedMovieList.addAll(mapped)

        featured = results.random()
    }
}