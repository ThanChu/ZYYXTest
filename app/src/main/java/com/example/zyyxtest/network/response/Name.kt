package com.example.zyyxtest.network.response

import com.google.gson.annotations.SerializedName

data class Name(
    @SerializedName("title")
    var title: String? = null,

    @SerializedName("first")
    var firstName: String? = null,

    @SerializedName("last")
    var lastName: String? = null
)