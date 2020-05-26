package com.example.zyyxtest.network.response

import com.google.gson.annotations.SerializedName

data class UserList(
    @SerializedName("results")
    val results: List<User>? = null
)