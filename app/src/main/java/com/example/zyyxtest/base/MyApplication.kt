package com.example.zyyxtest.base

import android.app.Application

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Navigator.context = this.applicationContext
    }
}