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
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class HomeActivity : AppCompatActivity(),KodeinAware {
    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater)}


    override val kodein: Kodein by kodein()
    private lateinit var viewModel: CostViewModel
    private val costFactory: CostViewModelFactory by instance()

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
        viewModel = ViewModelProvider(this,costFactory).get(CostViewModel::class.java)
    }

}