package com.example.carrot_market.data

import androidx.annotation.DrawableRes

data class Post(
    val id: Int,
    @DrawableRes
    val image: Int,
    val name: String,
    val content: String,
    val price: Int,
    val seller: String,
    val address: String,
    val likeCount: Int,
    val chatCount: Int,
)