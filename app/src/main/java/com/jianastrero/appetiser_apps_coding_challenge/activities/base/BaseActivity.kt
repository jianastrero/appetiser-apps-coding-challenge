package com.jianastrero.appetiser_apps_coding_challenge.activities.base

import androidx.appcompat.app.AppCompatActivity
import com.jianastrero.appetiser_apps_coding_challenge.SETTINGS_LAST_ACTIVITY
import com.jianastrero.appetiser_apps_coding_challenge.SETTINGS_LAST_VISIT
import com.jianastrero.appetiser_apps_coding_challenge.extensions.toReadable
import com.jianastrero.appetiser_apps_coding_challenge.singletons.Settings
import java.util.Date

open class BaseActivity : AppCompatActivity() {

    override fun onPause() {
        super.onPause()

        Settings.put(SETTINGS_LAST_VISIT, Date().toReadable())
        Settings.put(SETTINGS_LAST_ACTIVITY, this::class.java.toString())
    }
}