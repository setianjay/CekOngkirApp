package com.setianjay.cekongkirapp.network.response

data class SubDistrictResponse(val rajaongkir: RajaOngkir) {
    data class RajaOngkir(
        val query: Query,
        val status: Status,
        val results: List<Results>
    ){
        data class Query(val city: String)

        data class Status(val code: Int, val description: String)

        data class Results(
            val subdistrict_id: String,
            val province_id: String,
            val province: String,
            val city_id: String,
            val city: String,
            val type: String,
            val subdistrict_name: String
        )
    }
}