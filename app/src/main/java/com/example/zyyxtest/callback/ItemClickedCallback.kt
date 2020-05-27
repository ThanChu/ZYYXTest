package com.example.zyyxtest.callback

import com.example.zyyxtest.network.response.User

interface ItemClickedCallback {
    fun addItem(user: User)
    fun removeItem(user: User)
}