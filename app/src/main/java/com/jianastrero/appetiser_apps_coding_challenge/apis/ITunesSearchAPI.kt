package com.jianastrero.appetiser_apps_coding_challenge.apis

import com.jianastrero.appetiser_apps_coding_challenge.models.ITunesSearch
import retrofit2.http.GET
import retrofit2.http.Path

interface ITunesSearchAPI {

    @GET(
        "search?term={term}&amp;country={country}&amp;media={media}&amp;all"
    )
    fun search(
        @Path("term") term: String,
        @Path("country") country: String,
        @Path("media") media: String
    ): ITunesSearch
}