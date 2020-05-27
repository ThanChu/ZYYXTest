package com.example.zyyxtest.ui.main

import android.util.Log
import com.example.zyyxtest.data.DataLogined
import com.example.zyyxtest.network.RetrofitBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter(private val view: MainView) {

    fun handleLogout(){
        DataLogined.setLoginStatus(false)
        view.logout()
    }
}