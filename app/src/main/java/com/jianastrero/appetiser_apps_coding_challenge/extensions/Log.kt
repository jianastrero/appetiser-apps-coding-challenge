package com.jianastrero.appetiser_apps_coding_challenge.extensions

import android.util.Log
import com.jianastrero.appetiser_apps_coding_challenge.singletons.toJson

fun String?.log() {
    Log.d("Jian Astrero", toString())
}

fun Any?.log() {
    Log.d("Jian Astrero", toJson())
}