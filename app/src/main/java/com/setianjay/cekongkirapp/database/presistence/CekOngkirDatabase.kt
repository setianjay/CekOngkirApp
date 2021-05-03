package com.setianjay.cekongkirapp.database.presistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import org.kodein.di.Volatile

@Database(
    entities = [WayBillEntity::class],
    exportSchema = false,
    version = 1
)

abstract class CekOngkirDatabase: RoomDatabase() {

    abstract fun wayBillDao(): WayBillDao

    companion object{
        @Volatile private var instance: CekOngkirDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) =
        Room.databaseBuilder(context.applicationContext,
        CekOngkirDatabase::class.java,"CekOngkirApp.db")
            .allowMainThreadQueries()
            .build()
    }
}