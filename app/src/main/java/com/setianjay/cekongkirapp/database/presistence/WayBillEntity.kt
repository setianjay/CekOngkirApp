package com.setianjay.cekongkirapp.database.presistence

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tableWayBill")
data class WayBillEntity(
    @PrimaryKey(autoGenerate = false)
    val wayBill: String,
    val courier: String = "",
    val status: String = ""
)