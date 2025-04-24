package com.elite.elitebank

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EliteApplication : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}
