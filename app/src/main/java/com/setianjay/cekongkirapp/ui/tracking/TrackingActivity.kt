package com.setianjay.cekongkirapp.ui.tracking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.setianjay.cekongkirapp.R
import com.setianjay.cekongkirapp.databinding.ActivityTrackingBinding
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class TrackingActivity : AppCompatActivity(), KodeinAware {
    private val binding by lazy { ActivityTrackingBinding.inflate(layoutInflater) }
    override val kodein by kodein()
    private lateinit var viewModel: TrackingViewModel
    private val viewModelFactory: TrackingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
        setupViewModel()
    }

    private fun setupView(){
        supportActionBar!!.setTitle(R.string.lacak_no_resi)
    }

    private fun setupViewModel(){
        viewModel = ViewModelProvider(this,viewModelFactory).get(TrackingViewModel::class.java)
    }
}