package com.jianastrero.appetiser_apps_coding_challenge.viewmodels.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jianastrero.appetiser_apps_coding_challenge.viewmodels.MainViewModel
import java.lang.RuntimeException

object MyViewModelFactory {

    private lateinit var factory: ViewModelProvider.AndroidViewModelFactory

    fun init(application: Application) {
        if (!::factory.isInitialized)
            factory = object : ViewModelProvider.AndroidViewModelFactory(application) {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                    when (modelClass) {
                        MainViewModel::class.java -> MainViewModel(application)
                        else -> throw IllegalStateException("Unknown ViewModel: $modelClass")
                    } as T
            }
    }

    fun getInstance() = factory
}