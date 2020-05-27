package com.example.zyyxtest.ui.fragment.search

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zyyxtest.R
import com.example.zyyxtest.base.Navigator
import com.example.zyyxtest.callback.ItemClickedCallback
import com.example.zyyxtest.network.RetrofitBuilder
import com.example.zyyxtest.network.response.User
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_search.*
import java.util.*
import java.util.concurrent.TimeUnit

class SearchFragment : Fragment(), ItemClickedCallback {

    private var searchAdapter: SearchAdapter? = null
    private var callback: ItemClickedCallback? = null

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is ItemClickedCallback) {
            callback =  context as ItemClickedCallback
        } else {
            throw RuntimeException(context!!.toString() )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Cancel button clicked
        btn_cancel.setOnClickListener{
            fragmentManager?.popBackStack()
            edt_search.clearFocus()
            hideKeyboard(edt_search)
        }

        //Setup recycler view
        rcl_result.layoutManager = LinearLayoutManager(Navigator.context)
        searchAdapter = SearchAdapter(Collections.emptyList(), this)
        rcl_result.adapter = searchAdapter

        //Listener input password
        edt_search.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // Handle search
                Completable
                    .timer(2, TimeUnit.SECONDS)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        // search after 2 second
                        getUserList("1", "10", s.toString())
                    }, {
                        // show message when get error
                    })
            }
        })
    }

    private fun hideKeyboard(view: View) {
        view?.apply {
            val imm = Navigator.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    /**
     * Set data for result list
     */
    private fun setData(list: List<User>) {
        list?.let {
            searchAdapter?.setData(list)
        }
    }

    fun getUserList(page: String, results: String, seed: String) {

        RetrofitBuilder.buildService().getUserList(page, results, seed)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> result?.results?.let { setData(it) } },
                { error -> Log.i("TAG", "Login error: ${error.message}") },
                { Log.i("TAG", "Login Completed") }
            )
    }

    override fun addItem(user: User) {
        callback?.addItem(user)
    }

    override fun removeItem(user: User) {
        callback?.removeItem(user)
    }
}