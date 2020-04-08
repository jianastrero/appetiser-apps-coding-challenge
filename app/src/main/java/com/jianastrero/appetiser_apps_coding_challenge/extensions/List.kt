package com.jianastrero.appetiser_apps_coding_challenge.extensions

fun <T> Collection<T>.random(count: Int): Collection<T> {
    val set = mutableSetOf<T>()

    while (set.size < 10)
        set.add(random())

    return set
}