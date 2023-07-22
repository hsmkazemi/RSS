package com.rss.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rss.api.API
import com.rss.model.News
import com.rss.adapter.NewsAdaptor
import com.rss.R
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private val newsList = mutableListOf<News>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycleScope.launch {
            val progressBar = findViewById<ProgressBar>(R.id.progressBar)
            progressBar.isVisible = true
            val response = Retrofit.Builder().baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create()).build().create(API::class.java)
                .getNews("techcrunch", getString(R.string.API_Key))
            if (response.isSuccessful && response.body() != null) {
                newsList.addAll(response.body()!!.articles)
                initialRecView()
                progressBar.isVisible = false
            } else {
                Log.e("responseTest", "error to get the information")
            }
        }
    }

    private fun initialRecView() {
        val recView = findViewById<RecyclerView>(R.id.recView)
        val newsAdaptor = NewsAdaptor(newsList, this)
        recView.adapter = newsAdaptor
        recView.layoutManager = LinearLayoutManager(this)
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        recView.addItemDecoration(dividerItemDecoration)
    }
}