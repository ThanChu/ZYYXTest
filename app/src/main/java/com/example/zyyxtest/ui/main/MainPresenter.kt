package com.example.zyyxtest.ui.main

import android.util.Log
import com.example.zyyxtest.network.RetrofitBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainPresenter(view: MainView) {

    fun getUserList(page: String, results: String, seed: String) {
        CompositeDisposable().add(
            RetrofitBuilder.buildService().getUserList(page, results, seed)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> Log.i("TAG", "${result?.results?.get(0)?.email}") },
                    { error -> Log.i("TAG", "Login error: ${error.message}") },
                    { Log.i("TAG", "Login Completed") }
                ))
    }
}