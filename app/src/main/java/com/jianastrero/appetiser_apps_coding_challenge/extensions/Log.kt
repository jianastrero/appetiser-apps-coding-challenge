package com.jianastrero.appetiser_apps_coding_challenge.extensions

import android.util.Log
import com.jianastrero.appetiser_apps_coding_challenge.singletons.toJson

/**
 * Log any string
 */
fun String?.log() {
    Log.d("Jian Astrero", toString())
}

/**
 * Log anything
 */
fun Any?.log() {
    Log.d("Jian Astrero", toJson())
}