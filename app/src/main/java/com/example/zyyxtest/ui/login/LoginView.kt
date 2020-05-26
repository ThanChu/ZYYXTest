package com.example.zyyxtest.ui.login

interface LoginView {
    fun showMessage(message: String)
    fun isEnable(flag: Boolean)
    fun navigateToMain()
}