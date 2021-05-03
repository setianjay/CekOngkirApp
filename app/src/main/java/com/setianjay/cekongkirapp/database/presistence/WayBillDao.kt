package com.setianjay.cekongkirapp.database.presistence

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface WayBillDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(wayBillEntity: WayBillEntity)

    @Update
    suspend fun update(wayBillEntity: WayBillEntity)

    @Delete
    suspend fun delete(wayBillEntity: WayBillEntity)

    @Query("SELECT * FROM tableWayBill")
    fun select(): LiveData<List<WayBillEntity>>
}