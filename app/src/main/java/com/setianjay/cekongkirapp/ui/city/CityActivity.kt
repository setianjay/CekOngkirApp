package com.setianjay.cekongkirapp.ui.city

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.setianjay.cekongkirapp.R

class CityActivity : AppCompatActivity() {
    private val viewModel by lazy { ViewModelProvider(this).get(CityViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_city)
        viewModel.titleBar.observe(this){title:String ->
            supportActionBar!!.title = title
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}