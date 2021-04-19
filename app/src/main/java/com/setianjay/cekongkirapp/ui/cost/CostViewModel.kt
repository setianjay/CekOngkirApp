package com.setianjay.cekongkirapp.ui.cost

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.setianjay.cekongkirapp.database.preferences.PreferencesModel
import com.setianjay.cekongkirapp.network.repository.RajaOngkirRepository
import timber.log.Timber

class CostViewModel(
    private val repository: RajaOngkirRepository
): ViewModel() {
    val preferences: MutableLiveData<List<PreferencesModel>> = MutableLiveData()

    fun getPreferences(){
        preferences.value = repository.getPreferences()
    }

    override fun onCleared() {
        super.onCleared()
        Timber.e("CostViewModel: ViewModel is destroyed")
    }
}