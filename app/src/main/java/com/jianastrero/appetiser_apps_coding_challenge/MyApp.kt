package com.jianastrero.appetiser_apps_coding_challenge

import android.app.Application
import com.jianastrero.appetiser_apps_coding_challenge.singletons.Settings
import com.jianastrero.appetiser_apps_coding_challenge.viewmodels.factory.MyViewModelFactory

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Settings.init(this)
        MyViewModelFactory.init(this)
    }
}