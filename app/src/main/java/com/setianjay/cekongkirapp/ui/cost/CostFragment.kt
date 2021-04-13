package com.setianjay.cekongkirapp.ui.cost

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setianjay.cekongkirapp.R
import com.setianjay.cekongkirapp.databinding.FragmentCostBinding
import com.setianjay.cekongkirapp.ui.city.CityActivity


class CostFragment : Fragment() {
    private lateinit var binding: FragmentCostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCostBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
    }

    private fun initListener(){
        binding.etCityOrigin.setOnClickListener {
            Intent(context,CityActivity::class.java).also {
                startActivity(it)
            }
        }
    }

}