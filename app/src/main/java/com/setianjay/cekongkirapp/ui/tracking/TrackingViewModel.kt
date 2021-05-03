package com.setianjay.cekongkirapp.ui.tracking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.setianjay.cekongkirapp.database.presistence.WayBillEntity
import com.setianjay.cekongkirapp.network.repository.RajaOngkirRepository
import com.setianjay.cekongkirapp.network.resource.Resource
import com.setianjay.cekongkirapp.network.response.WayBillResponse
import kotlinx.coroutines.launch
import java.lang.Exception

class TrackingViewModel(
    private val repository: RajaOngkirRepository
) : ViewModel() {

    val wayBillResponse: MutableLiveData<Resource<WayBillResponse>> = MutableLiveData()
    val waybill: LiveData<List<WayBillEntity>> = repository.getWayBill()

    fun fetchWayBill(waybill: String, courier: String) = viewModelScope.launch {
        wayBillResponse.value = Resource.Loading()
        try {
            val response = repository.fetchWayBill(waybill, courier)
            wayBillResponse.value = Resource.Success(response.body()!!)
            saveWayBill(response.body()!!.rajaongkir)
        } catch (e: Exception) {
            wayBillResponse.value = Resource.Error(e.message.toString())
        }
    }

    fun saveWayBill(entity: WayBillResponse.RajaOngkir) {
        viewModelScope.launch {
            repository.saveWayBill(
                WayBillEntity(
                    wayBill = entity.query.waybill,
                    courier = entity.query.courier,
                    status = entity.result.delivery_status.status
                )
            )
        }
    }

    fun deleteWayBill(entity: WayBillEntity){
        viewModelScope.launch {
            repository.deleteWayBill(entity)
        }
    }

}