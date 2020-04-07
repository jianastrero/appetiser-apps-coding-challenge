package com.jianastrero.appetiser_apps_coding_challenge.models

import com.google.gson.annotations.SerializedName

data class ITunesSearch(
    var resultCount: Int,
    @SerializedName("results") var movies: List<Movie>
)