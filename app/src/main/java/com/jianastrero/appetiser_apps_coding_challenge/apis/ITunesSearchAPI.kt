package com.jianastrero.appetiser_apps_coding_challenge.apis

import com.jianastrero.appetiser_apps_coding_challenge.models.ITunesSearch
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesSearchAPI {

    @GET("search")
    suspend fun search(
        @Query("term") term: String,
        @Query("country") country: String,
        @Query("media") media: String,
        @Query("all") all: String
    ): ITunesSearch
}