package com.example.zyyxtest.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zyyxtest.R
import com.example.zyyxtest.base.Navigator
import com.example.zyyxtest.callback.DetailCallback
import com.example.zyyxtest.callback.ItemClickedCallback
import com.example.zyyxtest.network.response.User

class MainAdapter(private var list: List<User>, private val callback: DetailCallback) :
    RecyclerView.Adapter<MainAdapter.MovieViewHolder>() {

    fun setData(list: List<User>) {
        this.list = list
        notifyDataSetChanged()
    }

    fun addData(user: User) {
        this.list?.let {
            list += user
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val user: User = list[position]
        holder.tvFullName?.text = user?.name?.getFullName()
        holder.tvEmail?.text = user?.email
        holder.imgProfile?.let {
            Glide
                .with(Navigator.context)
                .load(user.picture?.medium)
                .into(it)
        }
        holder.itemView.setOnClickListener { callback.itemClicked(user) }
    }

    override fun getItemCount(): Int = list?.size


    class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_book_mark, parent, false)) {
        var tvFullName: TextView? = null
        var tvEmail: TextView? = null
        var imgProfile: ImageView? = null

        init {
            tvFullName = itemView.findViewById(R.id.tv_full_name)
            tvEmail = itemView.findViewById(R.id.tv_email)
            imgProfile = itemView.findViewById(R.id.img_profile)
        }

    }
}