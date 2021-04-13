package com.setianjay.cekongkirapp.ui.city

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setianjay.cekongkirapp.R
import com.setianjay.cekongkirapp.databinding.FragmentCityBinding

class CityFragment : Fragment() {
    private lateinit var binding: FragmentCityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCityBinding.inflate(inflater,container,false)
        return binding.root
    }
}