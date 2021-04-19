package com.setianjay.cekongkirapp.app

import android.app.Application
import com.setianjay.cekongkirapp.BuildConfig
import com.setianjay.cekongkirapp.database.preferences.CostPreferences
import com.setianjay.cekongkirapp.network.api.ApiService
import com.setianjay.cekongkirapp.network.api.RajaOngkirEndPoint
import com.setianjay.cekongkirapp.network.repository.RajaOngkirRepository
import com.setianjay.cekongkirapp.ui.city.CityViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import timber.log.Timber

class App: Application(), KodeinAware{



    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@App))

        bind<RajaOngkirEndPoint>() with singleton { ApiService.getClient() } // Api
        bind() from singleton { CostPreferences( instance() ) } // CostPreferences
        bind() from singleton { RajaOngkirRepository(instance(), instance()) } // Repository
        bind() from singleton { CityViewModelFactory(instance()) }

    }
}