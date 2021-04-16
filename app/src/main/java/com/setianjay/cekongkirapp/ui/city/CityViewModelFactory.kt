package com.setianjay.cekongkirapp.ui.city

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.setianjay.cekongkirapp.network.api.RajaOngkirEndPoint
import com.setianjay.cekongkirapp.network.repository.RajaOngkirRepository

class CityViewModelFactory(
    private val repository: RajaOngkirRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CityViewModel(repository) as T
    }
}