package com.example.zyyxtest.ui.login

import com.example.zyyxtest.data.DataLogined
import com.example.zyyxtest.util.checkPassword

class LoginPresenter(private val view: LoginView) {

    /**
     * Check validate user name and password
     * userName: usertest
     * password: 6 numbers and no duplicate
     */
    fun checkLogin(userName: String, password: String) {
        if ("usertest" != userName?.trim()) {
            view.isEnable(false)
            return
        }

        if (!password.checkPassword()) {
            view.isEnable(false)
            return
        }

        view.isEnable(true)
    }

    /**
     * Navigate to Main Screen
     */
    fun handleNavigateToMainScreen(){
        //Todo handle something at here
        DataLogined.setLoginStatus(true)
        view.navigateToMain()
    }
}