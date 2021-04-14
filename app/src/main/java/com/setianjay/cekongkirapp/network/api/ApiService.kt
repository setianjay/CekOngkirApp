package com.setianjay.cekongkirapp.network.api

import com.google.gson.GsonBuilder
import com.setianjay.cekongkirapp.constant.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiService {
    companion object {
        fun getClient(): RajaOngkirEndPoint{
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
            .addInterceptor(logging)
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("key",Constants.API_KEY).build()
                    chain.proceed(request)
                }
                .build()

            val googleJson = GsonBuilder().serializeNulls().create()

            return Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(googleJson))
                .client(client)
                .build()
                .create(RajaOngkirEndPoint::class.java)

        }
    }
}