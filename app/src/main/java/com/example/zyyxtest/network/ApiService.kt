package com.example.zyyxtest.network

import com.example.zyyxtest.network.response.UserList
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("api/")
    fun getUserList(
        @Query("page") page: String,
        @Query("results") results: String,
        @Query("seed") seed: String
    ): Observable<UserList>
}