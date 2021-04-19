package com.setianjay.cekongkirapp.ui.subdistrict

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.setianjay.cekongkirapp.R
import com.setianjay.cekongkirapp.databinding.FragmentSubdistrictBinding
import com.setianjay.cekongkirapp.network.resource.Resource
import com.setianjay.cekongkirapp.network.response.SubDistrictResponse
import com.setianjay.cekongkirapp.ui.city.CityViewModel
import timber.log.Timber

class SubDistrictFragment : Fragment() {
    private val viewModel: CityViewModel by lazy {
        ViewModelProvider(requireActivity()).get(CityViewModel::class.java) // ViewModel
    }
    private lateinit var binding: FragmentSubdistrictBinding // ViewBinding
    private lateinit var subDistrictAdapter: SubDistrictAdapter // Adapter
    private val type by lazy { requireActivity().intent.getStringExtra("type") } // Data Intent
    private val cityId by lazy { requireArguments().getString("city_id") } // Data Intent
    private val cityName by lazy { requireArguments().getString("city_name") } // Data Intent

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
        setupView()
        setupRecycleView()
        setupObserve()
        setupListener()
    }

    private fun setupView(){
        viewModel.titleBar.postValue("Pilih Kecamatan")
        Timber.e("cityId: $cityId")
        Timber.e("cityName: $cityName")
    }

    private fun setupRecycleView(){
        subDistrictAdapter = SubDistrictAdapter(arrayListOf(),object : SubDistrictAdapter.OnAdapterListener{
            override fun onClick(data: SubDistrictResponse.RajaOngkir.Results) {
                viewModel.savePreferences(
                    type = type!!,
                    id = data.subdistrict_id,
                    name = "$cityName, ${data.subdistrict_name}"
                )
                requireActivity().finish()
            }
        })

        binding.rvSubdistrict.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            adapter = subDistrictAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupObserve(){
        viewModel.subDistrictResponse.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> {
                    binding.rlSubdistrict.isRefreshing = true
                    Timber.e("RajaOngkir: isLoading")
                }
                is Resource.Success -> {
                    binding.rlSubdistrict.isRefreshing = false
                    subDistrictAdapter.setData(it.data!!.rajaongkir.results)
                }
                is Resource.Error -> {
                    binding.rlSubdistrict.isRefreshing = false
                    Timber.e("RajaOngkir: isError")
                }
            }
        }
    }

    private fun setupListener(){
        binding.rlSubdistrict.setOnRefreshListener {
            viewModel.fetchSubDistrict(cityId!!)
        }
    }
}