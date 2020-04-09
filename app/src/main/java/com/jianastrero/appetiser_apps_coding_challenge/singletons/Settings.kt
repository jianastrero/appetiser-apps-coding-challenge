package com.jianastrero.appetiser_apps_coding_challenge.singletons

import android.content.Context
import android.content.SharedPreferences

/**
 * Settings singleton. Shared preferences with generics
 */
object Settings {
    private lateinit var sp: SharedPreferences

    /**
     * Initializes the shared preferences once, and only once. Preferred to be called on subclass
     * of Application, but anywhere will do as long as this is called before other functions
     * of this singleton is used.
     *
     * @param context Any non-null context
     */
    fun init(context: Context) {
        if (!Settings::sp.isInitialized)
            sp = context.getSharedPreferences("Preferences", Context.MODE_PRIVATE)
    }

    /**
     * Get a saved setting using a key or return the default if it doesn't exist
     *
     * @param T Any Class
     * @param key String key for the setting
     * @param default Default value for the setting
     * @return
     */
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

    /**
     * Save the setting value with the given key
     *
     * @param T Any Class
     * @param key String key for the setting
     * @param value Value for the setting
     */
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

    /**
     * Remove one or more values pointed by keys
     *
     * @param keys Keys of the values that needs to be removed
     */
    fun remove(vararg keys: String) {
        val edit = sp.edit()

        keys.forEach {
            edit.remove(it)
        }

        edit.apply()
    }
}