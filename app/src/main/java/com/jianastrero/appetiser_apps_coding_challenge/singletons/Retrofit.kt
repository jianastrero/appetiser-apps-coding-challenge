package com.jianastrero.appetiser_apps_coding_challenge.singletons

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.jianastrero.appetiser_apps_coding_challenge.singletons.Retrofit as MyRetrofit

/**
 * Retrofit singleton to provide a more robust way of creating objects from retrofit
 */
object Retrofit {

    val gson: Gson = GsonBuilder()
        .create()

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://itunes.apple.com/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    inline fun <reified T> create(): T =
        retrofit.create(T::class.java)
}

/**
 * Returns a json representation of an object
 *
 * @param T Any class
 * @return
 */
fun <T : Any> T?.toJson(): String =
    if (this == null)
        toString()
    else
        MyRetrofit.gson.toJson(this)

/**
 * Converts a json representation of an object to an actual object
 *
 * @param T Any class
 * @return
 */
inline fun <reified T> String.fromJson(): T =
    MyRetrofit.gson.fromJson(this, T::class.java)