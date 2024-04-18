package com.example.carrot_market.data.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Product(
    @DrawableRes val image: Int,
    val name: String,
    val content: String,
    val price: Int,
): Parcelable
