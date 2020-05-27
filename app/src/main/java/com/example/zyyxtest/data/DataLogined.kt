package com.example.zyyxtest.data

import android.content.Context
import android.content.SharedPreferences
import com.example.zyyxtest.base.Navigator

class DataLogined(private val context: Context) {
    companion object {
        private var PRIVATE_MODE = 0
        private val PREF_NAME = "Data Login"
        val sharedPref: SharedPreferences =
            Navigator.context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)


        @JvmStatic
        fun setLoginStatus(isLogined: Boolean) {
            val editor = sharedPref.edit()
            editor.putBoolean(PREF_NAME, isLogined)
            editor.apply()
        }

        fun getLoginStatus(): Boolean {
            return sharedPref.getBoolean(PREF_NAME, false)
        }
    }
}