package com.apripachkin.tuneinbrowser

import android.app.Application
import android.os.StrictMode
import dagger.hilt.android.HiltAndroidApp

import timber.log.Timber

@HiltAndroidApp
class TuneInBrowserApp : Application() {
    override fun onCreate() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyLog()
                    .build()
            )
            StrictMode.setVmPolicy(
                StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .build()
            )
            Timber.plant(Timber.DebugTree())
        }
        super.onCreate()
    }
}