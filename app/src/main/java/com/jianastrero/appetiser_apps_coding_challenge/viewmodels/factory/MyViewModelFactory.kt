package com.jianastrero.appetiser_apps_coding_challenge.viewmodels.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jianastrero.appetiser_apps_coding_challenge.repositories.iTunesSearchRepository
import com.jianastrero.appetiser_apps_coding_challenge.viewmodels.MainViewModel
import com.jianastrero.appetiser_apps_coding_challenge.viewmodels.MovieViewModel

object MyViewModelFactory {

    private lateinit var factory: ViewModelProvider.AndroidViewModelFactory

    private val iTunesSearchRepository: iTunesSearchRepository by lazy {
        iTunesSearchRepository()
    }

    fun init(application: Application) {
        if (!::factory.isInitialized)
            factory = object : ViewModelProvider.AndroidViewModelFactory(application) {
                override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                    when (modelClass) {
                        MainViewModel::class.java ->
                            MainViewModel(
                                application,
                                iTunesSearchRepository
                            )
                        MovieViewModel::class.java ->
                            MovieViewModel(
                                application
                            )
                        else -> throw IllegalStateException("Unknown ViewModel: $modelClass")
                    } as T
            }
    }

    fun getInstance() = factory
}