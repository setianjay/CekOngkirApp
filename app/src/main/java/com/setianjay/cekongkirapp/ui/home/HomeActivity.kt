package com.setianjay.cekongkirapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.setianjay.cekongkirapp.R
import com.setianjay.cekongkirapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupTabLayout()
    }

    private fun setupTabLayout(){
        val tabAdapter = HomeTabAdapter(supportFragmentManager,lifecycle)
        val tabTitles = arrayListOf<String>("Cek Ongkir","Cek Resi")
        binding.viewPager.adapter = tabAdapter

        TabLayoutMediator(binding.tabLayout,binding.viewPager){tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}