package com.setianjay.cekongkirapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.setianjay.cekongkirapp.R

class HomeActivity : AppCompatActivity() {
    private val tabLayout by lazy { findViewById<TabLayout>(R.id.tab_layout) }
    private val viewPager by lazy { findViewById<ViewPager2>(R.id.view_pager) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setupTabLayout()
    }

    private fun setupTabLayout(){
        val tabAdapter = HomeTabAdapter(supportFragmentManager,lifecycle)
        val tabTitles = arrayListOf<String>("Cek Ongkir","Cek Resi")
        viewPager.adapter = tabAdapter

        TabLayoutMediator(tabLayout,viewPager){tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }
}