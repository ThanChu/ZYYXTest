package com.example.zyyxtest.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.zyyxtest.R

class MainActivity : AppCompatActivity(), MainView {
    private var presenter: MainPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(this)
        presenter?.getUserList("1", "10", "nam")
    }
}
