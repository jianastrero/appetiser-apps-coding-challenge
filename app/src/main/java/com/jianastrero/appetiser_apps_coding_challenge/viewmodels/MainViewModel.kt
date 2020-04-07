package com.jianastrero.appetiser_apps_coding_challenge.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.jianastrero.appetiser_apps_coding_challenge.SETTINGS_LAST_VISIT
import com.jianastrero.appetiser_apps_coding_challenge.binding.NonNullObservableField
import com.jianastrero.appetiser_apps_coding_challenge.models.Result
import com.jianastrero.appetiser_apps_coding_challenge.singletons.Settings

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val lastVisit = NonNullObservableField("")

    val list = mutableListOf<Result>()

    fun reset() {
        lastVisit.set(Settings.get(SETTINGS_LAST_VISIT, "Never"))
        list.clear()
    }
}