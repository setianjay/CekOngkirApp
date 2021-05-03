package com.setianjay.cekongkirapp.app

import android.app.Application
import com.setianjay.cekongkirapp.BuildConfig
import com.setianjay.cekongkirapp.database.preferences.CostPreferences
import com.setianjay.cekongkirapp.database.presistence.CekOngkirDatabase
import com.setianjay.cekongkirapp.network.api.ApiService
import com.setianjay.cekongkirapp.network.api.RajaOngkirEndPoint
import com.setianjay.cekongkirapp.network.repository.RajaOngkirRepository
import com.setianjay.cekongkirapp.ui.city.CityViewModelFactory
import com.setianjay.cekongkirapp.ui.cost.CostViewModelFactory
import com.setianjay.cekongkirapp.ui.tracking.TrackingViewModelFactory
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

        // Api
        bind<RajaOngkirEndPoint>() with singleton { ApiService.getClient() } // RajaOngkir API

        // Preferences
        bind() from singleton { CostPreferences( instance() ) } // CostPreferences

        // Databases
        bind() from singleton { CekOngkirDatabase(instance()) } // CekOngkirDatabase

        // Repository
        bind() from singleton { RajaOngkirRepository(instance(), instance(), instance()) } // RajaOngkir Repository

        // ViewModelFactory
        bind() from singleton { CityViewModelFactory(instance()) } // CityViewModelFactory
        bind() from singleton { CostViewModelFactory(instance()) } // CostViewModelFactory
        bind() from singleton { TrackingViewModelFactory(instance()) } // TrackingViewModelFactory

    }
}