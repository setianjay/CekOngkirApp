package com.setianjay.cekongkirapp.ui.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setianjay.cekongkirapp.network.api.RajaOngkirEndPoint
import com.setianjay.cekongkirapp.network.resource.Resource
import com.setianjay.cekongkirapp.network.response.CityResponse
import kotlinx.coroutines.launch
import java.lang.Exception

class CityViewModel(
    private val api: RajaOngkirEndPoint
): ViewModel() {
    val titleBar: MutableLiveData<String> = MutableLiveData("")
    val cityResponse: MutableLiveData<Resource<CityResponse>> = MutableLiveData()

    init {
        fetchCity()
    }

    fun fetchCity() = viewModelScope.launch {
        cityResponse.value = Resource.Loading()
        try {
            cityResponse.value = Resource.Success(api.city().body()!!)
        }catch (e: Exception){
            cityResponse.value = Resource.Error(e.message.toString())
        }
    }
}