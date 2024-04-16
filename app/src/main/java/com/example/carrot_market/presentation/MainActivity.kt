package com.example.carrot_market.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.carrot_market.adapter.PostAdapter
import com.example.carrot_market.data.PostDataSource
import com.example.carrot_market.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val postAdapter = PostAdapter(PostDataSource.dummyData)

        with(binding.rvPosts) {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }
}