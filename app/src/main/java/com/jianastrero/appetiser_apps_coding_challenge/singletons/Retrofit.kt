package com.jianastrero.appetiser_apps_coding_challenge.singletons

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.jianastrero.appetiser_apps_coding_challenge.singletons.Retrofit as MyRetrofit

object Retrofit {

    val gson = GsonBuilder().create()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://itunes.apple.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    inline fun <reified T> create(): T =
        retrofit.create(T::class.java)
}

fun <T : Any> T?.toJson(): String =
    if (this == null)
        toString()
    else
        MyRetrofit.gson.toJson(this)

inline fun <reified T> String.fromJson(): T =
    MyRetrofit.gson.fromJson(this, T::class.java)