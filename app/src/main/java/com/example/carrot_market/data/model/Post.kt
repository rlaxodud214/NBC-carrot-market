package com.example.carrot_market.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(
    val id: Int,
    val likeCount: Int,
    val chatCount: Int,
    val isLikeActivate: Boolean = false,
): Parcelable
