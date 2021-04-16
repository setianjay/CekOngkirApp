package com.setianjay.cekongkirapp.ui.city

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.setianjay.cekongkirapp.R
import com.setianjay.cekongkirapp.database.preferences.CostPreferences
import com.setianjay.cekongkirapp.network.api.ApiService
import com.setianjay.cekongkirapp.network.repository.RajaOngkirRepository

class CityActivity : AppCompatActivity() {
    private val TAG = "CityActivity"
    private lateinit var viewModel: CityViewModel // View Model
    private lateinit var viewModelFactory: CityViewModelFactory // View Model Factory
    private lateinit var repository: RajaOngkirRepository // Repository
    private val api by lazy { ApiService.getClient() }
    private val preferences by lazy { CostPreferences(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
        setupViewModel()
        setupObserve()
    }

    private fun setupViewModel(){
        repository = RajaOngkirRepository(api, preferences)
        viewModelFactory = CityViewModelFactory(repository)
        viewModel = ViewModelProvider(this,viewModelFactory).get(CityViewModel::class.java)
    }

    private fun setupObserve(){
        viewModel.titleBar.observe(this){title:String ->
            supportActionBar!!.title = title
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}