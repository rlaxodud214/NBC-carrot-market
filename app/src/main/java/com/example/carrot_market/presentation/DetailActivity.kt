package com.example.carrot_market.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.carrot_market.R
import com.example.carrot_market.Util
import com.example.carrot_market.data.Post
import com.example.carrot_market.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private lateinit var post: Post

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initData()
        initView()
    }

    private fun initData() {
        if (intent.hasExtra("post")) {
            post = intent.getParcelableExtra<Post>("post")!!
        }
    }

    private fun initView() = with(binding) {
        with(post) {
            ivProductImage.setImageResource(image)
            ivUserProfileImage.setImageResource(R.drawable.img_user)
            tvUserName.text = seller
            tvUserAddress.text = address
            tvProductName.text = name
            tvProductContent.text = content
            tvProductPrice.text = Util.dec.format(price)
        }
    }
}