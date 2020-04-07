package com.jianastrero.appetiser_apps_coding_challenge.binding

import androidx.databinding.ObservableField

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