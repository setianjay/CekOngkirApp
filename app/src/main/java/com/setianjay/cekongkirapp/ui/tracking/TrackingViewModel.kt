package com.setianjay.cekongkirapp.ui.tracking

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setianjay.cekongkirapp.network.repository.RajaOngkirRepository
import com.setianjay.cekongkirapp.network.resource.Resource
import com.setianjay.cekongkirapp.network.response.WayBillResponse
import kotlinx.coroutines.launch
import java.lang.Exception

class TrackingViewModel(
    private val repository: RajaOngkirRepository
): ViewModel() {

    val wayBillResponse: MutableLiveData<Resource<WayBillResponse>> = MutableLiveData()

    fun fetchWayBill(waybill: String,courier: String) = viewModelScope.launch{
        wayBillResponse.value = Resource.Loading()
        try {
            val response = repository.fetchWayBill(waybill, courier)
            wayBillResponse.value = Resource.Success(response.body()!!)
        }catch (e: Exception){
            wayBillResponse.value = Resource.Error(e.message.toString())
        }
    }


}