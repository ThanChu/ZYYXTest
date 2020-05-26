package com.example.zyyxtest.network.response

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("gender")
    var gender: String? = null,

    @SerializedName("name")
    var name: Name? = null,

    @SerializedName("email")
    var email: String? = null,

    @SerializedName("phone")
    var phone: String? = null
)