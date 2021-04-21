package com.setianjay.cekongkirapp.ui.cost

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setianjay.cekongkirapp.database.preferences.PreferencesModel
import com.setianjay.cekongkirapp.network.repository.RajaOngkirRepository
import com.setianjay.cekongkirapp.network.resource.Resource
import com.setianjay.cekongkirapp.network.response.CostResponse
import kotlinx.coroutines.launch
import timber.log.Timber

class CostViewModel(
    private val repository: RajaOngkirRepository
): ViewModel() {
    val preferences: MutableLiveData<List<PreferencesModel>> = MutableLiveData()
    val costResponse: MutableLiveData<Resource<CostResponse>> = MutableLiveData()

    fun getPreferences(){
        preferences.value = repository.getPreferences()
    }

   fun fetchCost(
       origin: String,
       originType: String,
       destination: String,
       destinationType: String,
       weight: String,
       courier: String
   ) = viewModelScope.launch {
       costResponse.value = Resource.Loading()
       try {
           val response = repository.fetchCost(
               origin = origin,
               originType = originType,
               destination = destination,
               destinationType = destinationType,
               weight = weight,
               courier = courier)
           costResponse.value = Resource.Success(response.body()!!)
       }catch (e: Exception){
           costResponse.value = Resource.Error(e.message.toString())
       }
   }

    override fun onCleared() {
        super.onCleared()
        Timber.e("CostViewModel: ViewModel is destroyed")
    }
}