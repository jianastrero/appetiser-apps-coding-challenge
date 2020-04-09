package com.jianastrero.appetiser_apps_coding_challenge.repositories

import com.jianastrero.appetiser_apps_coding_challenge.apis.ITunesSearchAPI
import com.jianastrero.appetiser_apps_coding_challenge.singletons.Retrofit

/**
 * Minimal Repository for iTunes Search API
 */
class iTunesSearchRepository {

    private val api: ITunesSearchAPI = Retrofit.create()

    suspend fun search() =
        api.search(
            "star",
            "au",
            "movie",
            ""
        )
}