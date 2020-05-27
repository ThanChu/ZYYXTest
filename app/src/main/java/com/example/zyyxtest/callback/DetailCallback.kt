package com.example.zyyxtest.callback

import com.example.zyyxtest.network.response.User

interface DetailCallback {
    fun itemClicked(user: User)
}