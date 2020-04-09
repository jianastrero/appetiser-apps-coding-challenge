package com.jianastrero.appetiser_apps_coding_challenge.extensions

/**
 * Get [count] number of non-repeating random objects from a collection
 *
 * @param T Any Class
 * @param count Number of objects to return
 * @return [count] number of non-repeating random objects
 */
fun <T> Collection<T>.random(count: Int): Collection<T> {
    val set = mutableSetOf<T>()

    while (set.size < count)
        set.add(random())

    return set
}