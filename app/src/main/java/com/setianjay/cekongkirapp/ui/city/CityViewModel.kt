package com.setianjay.cekongkirapp.ui.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setianjay.cekongkirapp.network.api.RajaOngkirEndPoint
import com.setianjay.cekongkirapp.network.repository.RajaOngkirRepository
import com.setianjay.cekongkirapp.network.resource.Resource
import com.setianjay.cekongkirapp.network.response.CityResponse
import com.setianjay.cekongkirapp.network.response.SubDistrictResponse
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class CityViewModel(
    private val repository: RajaOngkirRepository
): ViewModel() {
    val titleBar: MutableLiveData<String> = MutableLiveData("")
    val cityResponse: MutableLiveData<Resource<CityResponse>> = MutableLiveData()
    val subDistrictResponse: MutableLiveData<Resource<SubDistrictResponse>> = MutableLiveData()

    init {
        fetchCity()
        Timber.e("CityViewModel: is created")
    }

    fun fetchCity() = viewModelScope.launch {
        cityResponse.value = Resource.Loading()
        try {
            val response = repository.fetchCity()
            cityResponse.value = Resource.Success(response.body()!!)
        }catch (e: Exception){
            cityResponse.value = Resource.Error(e.message.toString())
        }
    }

    fun fetchSubDistrict(id: String) = viewModelScope.launch {
        subDistrictResponse.value = Resource.Loading()
        try{
            val response = repository.fetchSubDistrict(city_id = id)
            subDistrictResponse.value = Resource.Success(response.body()!!)
        }catch (e: Exception){
            subDistrictResponse.value = Resource.Error(e.message.toString())
        }
    }

    fun savePreferences(type: String, id: String, name: String){
        repository.setPreferences(type,id,name)
    }

    override fun onCleared() {
        super.onCleared()
        Timber.e("CityViewModel: ViewModel is destroyed")
    }
}