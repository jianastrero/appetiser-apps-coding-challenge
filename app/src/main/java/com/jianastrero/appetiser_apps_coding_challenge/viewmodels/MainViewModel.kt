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
    val search = NonNullObservableField("")

    val filteredGenreList = mutableListOf<Pair<String, List<Movie>>>()
    val trendingList = mutableListOf<Movie>()
    var featured: Movie? = null

    fun reset() {
        lastVisit.set(Settings.get(SETTINGS_LAST_VISIT, "Never"))
        filteredGenreList.clear()
        trendingList.clear()
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
                .map {
                    it.primaryGenreName to
                        results
                            .filter { item ->
                                it.primaryGenreName == item.primaryGenreName
                            }
                            .toMutableList()
                            .sortedBy { item -> item.trackName }
                }

        trendingList.clear()
        trendingList.addAll(results.random(10))

        filteredGenreList.clear()
        filteredGenreList.addAll(mapped)

        featured = results.random()
    }
}