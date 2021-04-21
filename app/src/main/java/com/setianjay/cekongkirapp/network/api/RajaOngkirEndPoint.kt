package com.setianjay.cekongkirapp.network.api

import com.setianjay.cekongkirapp.network.response.CityResponse
import com.setianjay.cekongkirapp.network.response.CostResponse
import com.setianjay.cekongkirapp.network.response.SubDistrictResponse
import retrofit2.Response
import retrofit2.http.*

interface RajaOngkirEndPoint {

    @GET("city")
    suspend fun city(): Response<CityResponse>

    @GET("subdistrict")
    suspend fun subDistrict(
        @Query("city") city: String
    ): Response<SubDistrictResponse>

    @FormUrlEncoded
    @POST("cost")
    suspend fun cost(
        @Field("origin") origin: String,
        @Field("originType") originType: String,
        @Field("destination") destination: String,
        @Field("destinationType") destinationType: String,
        @Field("weight") weight: String,
        @Field("courier") courier: String
    ): Response<CostResponse>
}