package com.jianastrero.appetiser_apps_coding_challenge.singletons

import android.content.Context
import android.content.SharedPreferences

object Settings {
    private lateinit var sp: SharedPreferences

    fun init(context: Context) {
        if (!Settings::sp.isInitialized)
            sp = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE)
    }

    fun <T> get(key: String, default: T): T {
        return when (default) {
            is Boolean -> sp.getBoolean(key, default)
            is Float -> sp.getFloat(key, default)
            is Int -> sp.getInt(key, default)
            is Long -> sp.getLong(key, default)
            is String -> sp.getString(key, default)
            else -> default
        } as T
    }

    fun <T> put(key: String, value: T) {
        val edit = sp.edit()
        when (value) {
            is Boolean -> edit.putBoolean(key, value)
            is Float -> edit.putFloat(key, value)
            is Int -> edit.putInt(key, value)
            is Long -> edit.putLong(key, value)
            is String -> edit.putString(key, value)
        }
        edit.apply()
    }

    fun remove(vararg keys: String) {
        val edit = sp.edit()

        keys.forEach {
            edit.remove(it)
        }

        edit.apply()
    }
}