package com.setianjay.cekongkirapp.ui.city

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.setianjay.cekongkirapp.R
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class CityActivity : AppCompatActivity(), KodeinAware {

    private val TAG = "CityActivity"
    override val kodein by kodein()
    private lateinit var viewModel: CityViewModel // View Model
    private val viewModelFactory: CityViewModelFactory by instance() // View Model Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
        setupViewModel()
        setupObserve()
    }

    private fun setupViewModel(){
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