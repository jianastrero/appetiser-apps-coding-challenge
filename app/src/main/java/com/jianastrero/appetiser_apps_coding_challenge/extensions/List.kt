package com.jianastrero.appetiser_apps_coding_challenge.extensions

fun <T> Collection<T>.random(count: Int): Collection<T> {
    val list = mutableListOf<T>()

    (0 until count).forEach { _ ->
        list.add(random())
    }

    return list
}