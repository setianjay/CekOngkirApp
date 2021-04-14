package com.setianjay.cekongkirapp.network.resource

sealed class Resource<T>(
    val data: T? = null,
    val message: T? = null
){
    class Loading<T>: Resource<T>()
    class Success<T>(data: T): Resource<T>(data)
    class Error<T>(message: T? = null, data: T? = null): Resource<T>(data,message)
}