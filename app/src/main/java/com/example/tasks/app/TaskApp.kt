package com.example.tasks.app

import com.example.tasks.app.base.BaseApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TaskApp: BaseApplication() {
    override fun getBaseUrl(): String = ""

    override fun onCreate() {
        super.onCreate()

//        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
//        else Timber.plant(CrashReportingTree())
    }
}