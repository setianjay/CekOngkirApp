package com.setianjay.cekongkirapp.ui.city

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.setianjay.cekongkirapp.R
import com.setianjay.cekongkirapp.databinding.FragmentCityBinding
import com.setianjay.cekongkirapp.network.resource.Resource
import com.setianjay.cekongkirapp.network.response.CityResponse
import timber.log.Timber

class CityFragment : Fragment() {
    private val viewModel: CityViewModel by lazy { ViewModelProvider(requireActivity()).get(CityViewModel::class.java) } // View Model
    private lateinit var binding: FragmentCityBinding // ViewBinding
    private lateinit var cityAdapter: CityAdapter

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
        initView()
        setupRecycleView()
        setupObserve()
        initListener()
    }

    private fun initView(){
        viewModel.titleBar.postValue("Pilih Kota")
    }

    private fun setupRecycleView(){
        cityAdapter = CityAdapter(arrayListOf(),object : CityAdapter.OnAdapterListener{
            override fun onClick(data: CityResponse.RajaOngkir.Results) {
                Timber.d("City : ${data.city_name}")
            }
        })

        binding.rvCity.apply {
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
            adapter = cityAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupObserve(){
        viewModel.cityResponse.observe(viewLifecycleOwner){
            when(it){
                is Resource.Loading -> {
                    Timber.e("rajaOngkir: isLoading")
                }
                is Resource.Success -> {
//                    Timber.e("RajaOngkir: ${it.data!!.rajaongkir}")
                    cityAdapter.setData(it.data!!.rajaongkir.results)
                }
                is Resource.Error -> {
                    Timber.e("RajaOngkir: isError")
                }
            }
        }
    }


    private fun initListener(){
        binding.etFindCity.doAfterTextChanged {

            cityAdapter.filter.filter(it.toString())
        }

        binding.container.setOnClickListener {
            findNavController().navigate(R.id.action_cityFragment_to_subDistrictFragment)
        }
    }
}