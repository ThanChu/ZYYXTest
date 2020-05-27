package com.example.zyyxtest.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zyyxtest.R
import com.example.zyyxtest.base.BaseActivity
import com.example.zyyxtest.callback.DetailCallback
import com.example.zyyxtest.callback.ItemClickedCallback
import com.example.zyyxtest.network.response.User
import com.example.zyyxtest.ui.fragment.search.SearchFragment
import com.example.zyyxtest.ui.login.LoginActivity
import com.example.zyyxtest.ui.profile.ProfileActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : BaseActivity(), MainView, ItemClickedCallback, DetailCallback {
    private var presenter: MainPresenter? = null
    private var mainAdapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {
        presenter = MainPresenter(this)

        //Log out button clicked
        btn_login_out.setOnClickListener { presenter?.handleLogout() }

        //Listener focus change of search edit text
        btn_search.setOnClickListener {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.frame_main, SearchFragment.newInstance(), "SEARCH")
                .addToBackStack("SEARCH")
                .commit()
        }

        //Setup recycler view
        rcl_book_mark.layoutManager = LinearLayoutManager(this)
        mainAdapter = MainAdapter(Collections.emptyList(), this)
        rcl_book_mark.adapter = mainAdapter
    }

    override fun logout() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun addItem(user: User) {
        mainAdapter?.addData(user)
    }

    override fun removeItem(user: User) {
        showMessage("${user.email}")
    }

    override fun itemClicked(user: User) {
        val intent = Intent(this, ProfileActivity::class.java)
        intent.putExtra("EXTRA_USER", user)
        startActivity(intent)
    }
}
