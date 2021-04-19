package com.setianjay.cekongkirapp.network.response

data class CostResponse(
    val rajaOngkir: RajaOngkir
) {
    data class RajaOngkir(
        val query: Query,
        val status: Status,
        val origin_details: OriginDetails,
        val destination_details: DestinationDetails,
        val results: List<Results>
    ){
        data class Query(
            val origin: String,
            val originType: String,
            val destination: String,
            val destinationType: String,
            val weight: Int,
            val courier: String
        )

        data class Status(
            val code: Int,
            val description: String
        )

        data class OriginDetails(
            val city_id: String,
            val province_id: String,
            val province: String,
            val type: String,
            val city_name: String,
            val postal_code: String
        )

        data class DestinationDetails(
            val subdistrict_id: String,
            val province_id: String,
            val province: String,
            val city_id: String,
            val city: String,
            val type: String,
            val subdistrict_name: String
        )

        data class Results(
            val code: String,
            val name: String,
            val costs: List<Costs>
        ){
            data class Costs(
                val service: String,
                val description: String,
                val cost: List<Cost>
            ){
                data class Cost(
                    val value: Int,
                    val etd: String,
                    val note: String
                )
            }
        }
    }
}