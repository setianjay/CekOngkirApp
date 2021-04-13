package com.setianjay.cekongkirapp.ui.subdistrict

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.setianjay.cekongkirapp.R
import com.setianjay.cekongkirapp.databinding.FragmentSubdistrictBinding
import com.setianjay.cekongkirapp.ui.city.CityViewModel

class SubDistrictFragment : Fragment() {
    private val viewModel: CityViewModel by lazy { ViewModelProvider(requireActivity()).get(CityViewModel::class.java) }
    private lateinit var binding: FragmentSubdistrictBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSubdistrictBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.titleBar.postValue("Pilih Kecamatan")
    }
}