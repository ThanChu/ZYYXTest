package com.example.zyyxtest.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import com.example.zyyxtest.R
import com.example.zyyxtest.base.BaseActivity
import com.example.zyyxtest.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginView {

    private var presenter: LoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()
    }

    private fun init() {
        presenter = LoginPresenter(this)

        //Listener input username
        edt_user_name.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                checkLogin()
            }
        })

        //Listener input password
        edt_password.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                checkLogin()
            }
        })

        //Handle button clicked
        btn_login.setOnClickListener {
            presenter?.handleNavigateToMainScreen()
        }
    }

    /**
     * username and password are correct -> enable login button
     * else -> disable login button
     */
    private fun checkLogin() = presenter?.checkLogin(edt_user_name?.text.toString(), edt_password?.text.toString())

    override fun isEnable(flag: Boolean) {
        btn_login.isEnabled = flag
    }

    override fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter = null
    }
}
