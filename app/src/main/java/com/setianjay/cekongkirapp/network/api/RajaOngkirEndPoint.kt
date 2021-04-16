package com.setianjay.cekongkirapp.network.api

import com.setianjay.cekongkirapp.network.response.CityResponse
import com.setianjay.cekongkirapp.network.response.SubDistrictResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RajaOngkirEndPoint {

    @GET("city")
    suspend fun city(): Response<CityResponse>

    @GET("subdistrict")
    suspend fun subDistrict(
        @Query("city") city: String
    ): Response<SubDistrictResponse>
}