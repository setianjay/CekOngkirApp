package com.setianjay.cekongkirapp.network.repository

import com.setianjay.cekongkirapp.constant.Constants
import com.setianjay.cekongkirapp.database.preferences.CostPreferences
import com.setianjay.cekongkirapp.database.preferences.PreferencesModel
import com.setianjay.cekongkirapp.network.api.RajaOngkirEndPoint
import com.setianjay.cekongkirapp.network.response.CityResponse
import com.setianjay.cekongkirapp.network.response.CostResponse
import com.setianjay.cekongkirapp.network.response.SubDistrictResponse
import retrofit2.Response

class RajaOngkirRepository(
    private val api: RajaOngkirEndPoint,
    private val preference: CostPreferences
) {

    suspend fun fetchCity(): Response<CityResponse>{
        return api.city()
    }

    suspend fun fetchSubDistrict(city_id: String): Response<SubDistrictResponse>{
        return api.subDistrict(city_id)
    }

    fun setPreferences(type: String, id: String, name: String){
        when(type){
            "origin" -> {
                preference.putString(Constants.PREF_ORIGIN_ID,id)
                preference.putString(Constants.PREF_ORIGIN_NAME, name)
            }
            "destination" -> {
                preference.putString(Constants.PREF_DESTINATION_ID,id)
                preference.putString(Constants.PREF_DESTINATION_NAME, name)
            }
        }
    }

    fun getPreferences(): List<PreferencesModel>{
        return listOf(
            PreferencesModel("origin",preference.getString(Constants.PREF_ORIGIN_ID),preference.getString(Constants.PREF_ORIGIN_NAME)),
            PreferencesModel("destination",preference.getString(Constants.PREF_DESTINATION_ID),preference.getString(Constants.PREF_DESTINATION_NAME))
        )
    }

    fun clearPreferences(){
        preference.clearPreferences()
    }

    suspend fun fetchCost(
        origin: String,
        originType: String,
        destination: String,
        destinationType: String,
        weight: String,
        courier: String
    ) = api.cost(origin,originType,destination,destinationType,weight,courier)
}