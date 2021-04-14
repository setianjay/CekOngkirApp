package com.setianjay.cekongkirapp.ui.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.setianjay.cekongkirapp.network.api.RajaOngkirEndPoint

class CityViewModel(
    private val api: RajaOngkirEndPoint
): ViewModel() {
    val titleBar: MutableLiveData<String> = MutableLiveData("")
}