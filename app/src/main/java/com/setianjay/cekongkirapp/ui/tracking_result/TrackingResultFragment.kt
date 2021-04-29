package com.setianjay.cekongkirapp.ui.tracking_result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setianjay.cekongkirapp.R
import com.setianjay.cekongkirapp.databinding.FragmentTrackingResultBinding

class TrackingResultFragment : Fragment() {
    private lateinit var binding: FragmentTrackingResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTrackingResultBinding.inflate(inflater,container,false)
        return binding.root
    }
}