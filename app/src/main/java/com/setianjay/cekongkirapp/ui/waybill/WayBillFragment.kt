package com.setianjay.cekongkirapp.ui.waybill

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.setianjay.cekongkirapp.databinding.FragmentWayBillBinding
import com.setianjay.cekongkirapp.ui.tracking.TrackingActivity


class WayBillFragment : Fragment() {
    private lateinit var binding: FragmentWayBillBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWayBillBinding.inflate(inflater,container,false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.etSearch.setOnClickListener {
            Intent(requireContext(),TrackingActivity::class.java).also {
                startActivity(it)
            }
        }
    }

}