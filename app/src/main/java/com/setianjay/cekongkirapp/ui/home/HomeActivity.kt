package com.setianjay.cekongkirapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.setianjay.cekongkirapp.R
import com.setianjay.cekongkirapp.databinding.ActivityHomeBinding
import com.setianjay.cekongkirapp.ui.cost.CostViewModel
import com.setianjay.cekongkirapp.ui.cost.CostViewModelFactory
import com.setianjay.cekongkirapp.ui.tracking.TrackingViewModel
import com.setianjay.cekongkirapp.ui.tracking.TrackingViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class HomeActivity : AppCompatActivity(),KodeinAware {
    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater)}


    override val kodein: Kodein by kodein()
    private lateinit var costViewModel: CostViewModel
    private lateinit var trackingViewModel: TrackingViewModel
    private val costFactory: CostViewModelFactory by instance()
    private val trackingFactory: TrackingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupTabLayout()
        setupViewModel()
    }

    private fun setupTabLayout(){
        val tabAdapter = HomeTabAdapter(supportFragmentManager,lifecycle)
        val tabTitles = arrayListOf<String>("Cek Ongkir","Cek Resi")
        binding.viewPager.adapter = tabAdapter

        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    private fun setupViewModel(){
        costViewModel = ViewModelProvider(this,costFactory).get(CostViewModel::class.java)
        costViewModel.clearPreference()

        trackingViewModel = ViewModelProvider(this,trackingFactory).get(TrackingViewModel::class.java)
    }

}