package com.setianjay.cekongkirapp.ui.tracking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.setianjay.cekongkirapp.network.repository.RajaOngkirRepository

class TrackingViewModelFactory(
    private val repository: RajaOngkirRepository
): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TrackingViewModel(repository) as T
    }
}