package com.setianjay.cekongkirapp.ui.subdistrict

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setianjay.cekongkirapp.R
import com.setianjay.cekongkirapp.databinding.FragmentSubdistrictBinding

class SubDistrictFragment : Fragment() {
    private lateinit var binding: FragmentSubdistrictBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSubdistrictBinding.inflate(inflater,container,false)
        return binding.root
    }
}