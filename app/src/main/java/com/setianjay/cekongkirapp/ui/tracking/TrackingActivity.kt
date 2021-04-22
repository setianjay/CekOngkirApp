package com.setianjay.cekongkirapp.ui.tracking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.setianjay.cekongkirapp.R
import com.setianjay.cekongkirapp.databinding.ActivityTrackingBinding

class TrackingActivity : AppCompatActivity() {
    private val binding by lazy { ActivityTrackingBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView(){
        supportActionBar!!.setTitle(R.string.lacak_no_resi)
    }
}