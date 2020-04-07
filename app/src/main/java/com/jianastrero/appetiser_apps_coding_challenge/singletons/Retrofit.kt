package com.jianastrero.appetiser_apps_coding_challenge.singletons

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {

    private val gson = GsonBuilder().create()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://itunes.apple.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    inline fun <reified T> create(): T =
        retrofit.create(T::class.java)
}