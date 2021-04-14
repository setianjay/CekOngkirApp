package com.setianjay.cekongkirapp.ui.city

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.setianjay.cekongkirapp.R
import com.setianjay.cekongkirapp.network.api.ApiService
import com.setianjay.cekongkirapp.network.resource.Resource
import timber.log.Timber

class CityActivity : AppCompatActivity() {
    private val TAG = "CityActivity"
    private lateinit var viewModel: CityViewModel // View Model
    private lateinit var viewModelFactory: CityViewModelFactory // View Model Factory
    private val api by lazy { ApiService.getClient() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
        setupViewModel()
        setupObserve()
    }

    private fun setupViewModel(){
        viewModelFactory = CityViewModelFactory(api)
        viewModel = ViewModelProvider(this,viewModelFactory).get(CityViewModel::class.java)
    }

    private fun setupObserve(){
        viewModel.titleBar.observe(this){title:String ->
            supportActionBar!!.title = title
        }

        viewModel.cityResponse.observe(this){
            when(it){
                is Resource.Loading -> {
                    Timber.e("rajaOngkir: isLoading")
                }
                is Resource.Success -> {
                    Timber.e("RajaOngkir: ${it.data!!.rajaongkir}")
                }
                is Resource.Error -> {
//                    Timber.e("RajaOngkir: isError")
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}