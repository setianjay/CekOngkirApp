package com.setianjay.cekongkirapp.ui.cost

import androidx.lifecycle.ViewModel
import com.setianjay.cekongkirapp.network.repository.RajaOngkirRepository
import timber.log.Timber

class CostViewModel(
    private val repository: RajaOngkirRepository
): ViewModel() {

    fun getPreferences(){
        repository.getPreferences()
    }

    override fun onCleared() {
        super.onCleared()
        Timber.e("CostViewModel: ViewModel is destroyed")
    }
}