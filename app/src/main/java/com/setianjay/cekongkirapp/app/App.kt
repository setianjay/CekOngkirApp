package com.setianjay.cekongkirapp.app

import android.app.Application
import com.setianjay.cekongkirapp.BuildConfig
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import timber.log.Timber

class App: Application(){

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}