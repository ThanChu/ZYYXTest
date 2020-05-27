package com.example.zyyxtest.ui.profile

import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.zyyxtest.R
import com.example.zyyxtest.base.BaseActivity
import com.example.zyyxtest.base.Navigator
import com.example.zyyxtest.network.response.User
import kotlinx.android.synthetic.main.activity_profile.*


class ProfileActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val user: User? = intent.getParcelableExtra("EXTRA_USER") as? User

        user?.let {
            tv_name?.text = user.name?.getFullName()
            tv_email.text = user.email
            tv_phone.text = user.phone
            Glide.with(Navigator.context)
                .load(user.picture?.medium)
                .into(img_profile)
        }
    }


}
