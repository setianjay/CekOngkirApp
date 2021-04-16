package com.setianjay.cekongkirapp.database.preferences

import android.content.Context
import android.content.SharedPreferences
import com.setianjay.cekongkirapp.constant.Constants

class CostPreferences(context: Context) {
    private val sharedPref = context.getSharedPreferences(Constants.PREF_NAME,Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor

    init {
        editor = sharedPref.edit()
    }

    fun putString(key: String, value: String){
        editor.apply{
            putString(key,value)
            apply()
        }
    }

    fun getString(key: String): String? {
        return sharedPref.getString(key,null)
    }
}