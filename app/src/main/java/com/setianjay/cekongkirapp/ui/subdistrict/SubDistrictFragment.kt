package com.setianjay.cekongkirapp.ui.subdistrict

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setianjay.cekongkirapp.R
import com.setianjay.cekongkirapp.databinding.FragmentSubDistrictBinding

class SubDistrictFragment : Fragment() {
    private lateinit var binding: FragmentSubDistrictBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSubDistrictBinding.inflate(inflater,container,false)
        return binding.root
    }
}