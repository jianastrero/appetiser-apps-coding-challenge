package com.jianastrero.appetiser_apps_coding_challenge.models

import com.google.gson.annotations.SerializedName

/**
 * Extracted from the JSON return of the api
 *
 * @property resultCount
 * @property movies
 */
data class ITunesSearch(
    var resultCount: Int,
    @SerializedName("results") var movies: List<Movie>
)