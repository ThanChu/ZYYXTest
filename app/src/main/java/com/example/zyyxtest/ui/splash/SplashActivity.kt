package com.example.zyyxtest.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.zyyxtest.R
import com.example.zyyxtest.base.Navigator
import com.example.zyyxtest.data.DataLogined
import com.example.zyyxtest.ui.login.LoginActivity
import com.example.zyyxtest.ui.main.MainActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Thread.sleep(1000)
        if (DataLogined.getLoginStatus()) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        finish()
    }
}
