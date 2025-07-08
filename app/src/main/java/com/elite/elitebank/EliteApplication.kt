package com.elite.elitebank

import android.app.Application
import com.elite.elitebank.security.SecurityUtils
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class EliteApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        SecurityUtils.initCameraDetector(applicationContext)
    }




}
