package com.setianjay.cekongkirapp.ui.city

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.setianjay.cekongkirapp.R
import com.setianjay.cekongkirapp.network.api.ApiService

class CityActivity : AppCompatActivity() {
    private lateinit var viewModel: CityViewModel // View Model
    private lateinit var viewModelFactory: CityViewModelFactory // View Model Factory
    private val api by lazy { ApiService.getClient() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
        setupViewModel()
        viewModel.titleBar.observe(this){title:String ->
            supportActionBar!!.title = title
        }
    }

    private fun setupViewModel(){
        viewModelFactory = CityViewModelFactory(api)
        viewModel = ViewModelProvider(this,viewModelFactory).get(CityViewModel::class.java)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}