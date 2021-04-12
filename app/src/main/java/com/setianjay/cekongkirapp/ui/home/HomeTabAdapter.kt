package com.setianjay.cekongkirapp.ui.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.setianjay.cekongkirapp.ui.cost.CostFragment
import com.setianjay.cekongkirapp.ui.waybill.WayBillFragment

class HomeTabAdapter(
    fragmentManager: FragmentManager,lifecycle: Lifecycle
): FragmentStateAdapter(fragmentManager,lifecycle) {
    val listFragments = mutableListOf<Fragment>(
        CostFragment(),
        WayBillFragment()
    )

    override fun getItemCount(): Int {
        return listFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        return listFragments[position]
    }
}