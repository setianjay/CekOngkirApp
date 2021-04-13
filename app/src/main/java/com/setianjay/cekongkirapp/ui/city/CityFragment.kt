package com.setianjay.cekongkirapp.ui.city

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.setianjay.cekongkirapp.R
import com.setianjay.cekongkirapp.databinding.FragmentCityBinding

class CityFragment : Fragment() {
    private val viewModel: CityViewModel by lazy { ViewModelProvider(requireActivity()).get(CityViewModel::class.java) }
    private lateinit var binding: FragmentCityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCityBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.titleBar.postValue("Pilih Kota")
        initListener()
    }

    private fun initListener(){
        binding.container.setOnClickListener {
            findNavController().navigate(R.id.action_cityFragment_to_subDistrictFragment)
        }
    }
}