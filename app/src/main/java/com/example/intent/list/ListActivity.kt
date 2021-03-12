package com.example.intent.list

import android.os.Bundle
import android.text.TextUtils
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.intent.R
import com.example.intent.databinding.ActivityListBinding
import java.util.*


class ListActivity : AppCompatActivity() {
    private var customAdapter: ListAdapter? = null
    private lateinit var binding: ActivityListBinding

    private val viewModel: ListViewModel by lazy {
        ViewModelProvider(this).get(ListViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(applicationContext, 1)
        binding.recycleView.layoutManager = mLayoutManager
        binding.recycleView.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        binding.recycleView.itemAnimator = DefaultItemAnimator()
        binding.searchView.setOnQueryTextListener(listener)

        viewModel.results.observe(this, {
            it?.let { response ->
                customAdapter = ListAdapter(applicationContext, response)
                binding.recycleView.adapter = customAdapter
            }
        })

        viewModel.responseStatus.observe(this, {
            it?.let { response ->
                if (!response) {
                    Toast.makeText(this, R.string.response_error_message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private var listener: SearchView.OnQueryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextChange(query: String): Boolean {
            if(!TextUtils.isEmpty(query)) {
                viewModel.searchByName(query.toLowerCase(Locale.getDefault()))
            }
            return true
        }

        override fun onQueryTextSubmit(query: String): Boolean {
            return false
        }
    }
}