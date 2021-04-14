package com.setianjay.cekongkirapp.network.api

import com.setianjay.cekongkirapp.network.response.CityResponse
import retrofit2.Response
import retrofit2.http.GET

interface RajaOngkirEndPoint {

    @GET("city")
    suspend fun getCity(): Response<CityResponse>
}