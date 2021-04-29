package com.setianjay.cekongkirapp.ui.tracking

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.setianjay.cekongkirapp.R
import com.setianjay.cekongkirapp.databinding.FragmentTrackingBinding

class TrackingFragment : Fragment() {
    private lateinit var binding: FragmentTrackingBinding
    private val couriers by lazy { resources.getStringArray(R.array.courier) }
    private lateinit var selectedCourier: String
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTrackingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        setupAdapter()
    }

    private fun setupAdapter(){
        // Spinner
        val spinnerAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,couriers)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.listCourier.adapter = spinnerAdapter

    }

    private fun initListener(){
        binding.listCourier.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?){}

            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                selectedCourier = parent.getItemAtPosition(position).toString()
            }
        }
    }
}