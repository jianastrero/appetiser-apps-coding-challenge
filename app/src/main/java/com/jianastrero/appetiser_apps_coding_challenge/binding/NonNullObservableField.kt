package com.jianastrero.appetiser_apps_coding_challenge.binding

import androidx.databinding.ObservableField

/**
 * A non null subclass of observable field such that data will never be null
 * @param T Any Class
 *
 * @constructor
 * @param value Primary value of the observable field
 */
class NonNullObservableField<T>(value: T) : ObservableField<T>() {
    init {
        set(value)
    }

    override fun get(): T {
        return super.get()!!
    }

    override fun set(value: T) {
        super.set(value!!)
    }
}