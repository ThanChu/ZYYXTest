package com.example.zyyxtest.ui.fragment.search

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zyyxtest.R
import com.example.zyyxtest.callback.ItemClickedCallback
import com.example.zyyxtest.network.response.User

class SearchAdapter(private var list: List<User>, private val callback: ItemClickedCallback) :
    RecyclerView.Adapter<SearchAdapter.MovieViewHolder>() {
    fun setData(list: List<User>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(inflater, parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val user: User = list[position]
        holder.tvFullName?.text = user?.name?.getFullName()
        holder.cbBookMark?.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                callback.addItem(user)
            } else {
                callback.removeItem(user)
            }
        }
    }

    override fun getItemCount(): Int = list?.size


    class MovieViewHolder(inflater: LayoutInflater, parent: ViewGroup) :
        RecyclerView.ViewHolder(inflater.inflate(R.layout.item_search_result, parent, false)) {
        var tvFullName: TextView? = null
        var cbBookMark: CheckBox? = null


        init {
            tvFullName = itemView.findViewById(R.id.tv_full_name)
            cbBookMark = itemView.findViewById(R.id.cb_book_mark)
        }

    }
}