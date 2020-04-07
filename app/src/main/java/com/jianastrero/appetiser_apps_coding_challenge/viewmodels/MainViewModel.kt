package com.jianastrero.appetiser_apps_coding_challenge.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.jianastrero.appetiser_apps_coding_challenge.SETTINGS_LAST_VISIT
import com.jianastrero.appetiser_apps_coding_challenge.binding.NonNullObservableField
import com.jianastrero.appetiser_apps_coding_challenge.models.Result
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

    val list = mutableListOf<Result>()

    fun reset() {
        lastVisit.set(Settings.get(SETTINGS_LAST_VISIT, "Never"))
        list.clear()
    }

    fun fetchData() = CoroutineScope(Dispatchers.IO).launch {
        val search = iTunesSearchRepository.search()

        val results = search.results.toMutableList().sortedBy { it.trackName }

        list.clear()
        list.addAll(results)
    }
}