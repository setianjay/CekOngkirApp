package com.setianjay.cekongkirapp.ui.cost

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.setianjay.cekongkirapp.R
import com.setianjay.cekongkirapp.database.preferences.PreferencesModel
import com.setianjay.cekongkirapp.databinding.FragmentCostBinding
import com.setianjay.cekongkirapp.network.resource.Resource
import com.setianjay.cekongkirapp.ui.city.CityActivity
import timber.log.Timber


class CostFragment : Fragment() {
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(CostViewModel::class.java) }
    private lateinit var binding: FragmentCostBinding
    private var originSubdistrictId: String? = ""
    private var destinationSubdistrictId: String? = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListener()
        setupObserve()
    }

    override fun onStart() {
        super.onStart()
        loading(false)
        viewModel.getPreferences()
    }

    private fun initListener() {
        binding.etCityOrigin.setOnClickListener {
            Intent(context, CityActivity::class.java).also {
                it.putExtra("type", "origin")
                startActivity(it)
            }
        }

        binding.etCityDestination.setOnClickListener {
            Intent(context, CityActivity::class.java).also {
                it.putExtra("type", "destination")
                startActivity(it)
            }
        }

        binding.btnCekOngkir.setOnClickListener {
            if (originSubdistrictId.isNullOrEmpty() || destinationSubdistrictId.isNullOrEmpty()){
                Toast.makeText(
                    requireContext(),
                    "Field can't empty, please fill it",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {
                viewModel.fetchCost(
                    origin = originSubdistrictId!!,
                    originType = "subdistrict",
                    destination = destinationSubdistrictId!!,
                    destinationType = "subdistrict",
                    weight = "1000",
                    courier = "sicepat:pos"
                )
            }
        }
    }

    private fun setupObserve() {
        viewModel.preferences.observe(viewLifecycleOwner
        ) { preferencesList: List<PreferencesModel> ->
            preferencesList.forEach {
                when (it.type) {
                    "origin" -> {
                        originSubdistrictId = it.id
                        binding.etCityOrigin.setText(it.name)
                    }
                    "destination" -> {
                        destinationSubdistrictId = it.id
                        binding.etCityDestination.setText(it.name)
                    }
                }
            }
        }

        viewModel.costResponse.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> {
                    loading(true)
                    Timber.e("CostAPI: isLoading")
                }
                is Resource.Success -> {
                    loading(false)
                    Timber.e("CostAPI: ${it.data!!.rajaongkir.results}")
                }
                is Resource.Error -> {
                    Timber.e("CostAPI: isError")
                }
            }
        }
    }

    private fun loading(isLoading: Boolean){
        if (isLoading) binding.progressCost.visibility = View.VISIBLE
        else binding.progressCost.visibility = View.GONE
    }
}