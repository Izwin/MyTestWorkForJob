package com.example.myappforjob.view

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide.init
import com.example.myappforjob.R
import com.example.myappforjob.adapters.PhotoAdapter
import com.example.myappforjob.model.PhotoModel
import com.example.myappforjob.webclient.WebHelper
import kotlinx.coroutines.runBlocking


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    var adapter: PhotoAdapter = PhotoAdapter(this)
    lateinit var swipeLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipeLayout = findViewById(R.id.swipe_to_refresh)
        recyclerView = findViewById(R.id.rec_view)

        val spanCount: Int = if (resources.getBoolean(R.bool.isTablet)) {
            3
        } else {
            2
        }
        recyclerView.setLayoutManager(GridLayoutManager(this, spanCount))

        refreshScreen()

        swipeLayout.setOnRefreshListener(object :
            SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                refreshScreen()
            }
        })

    }

    fun refreshScreen() {
        val lost: ArrayList<PhotoModel> = ArrayList()
        runBlocking { lost.addAll(WebHelper.getPhotoList()) }
        adapter.setArrayList(lost)
        recyclerView.adapter = adapter
        swipeLayout.isRefreshing = false
    }
}