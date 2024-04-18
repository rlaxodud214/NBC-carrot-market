package com.example.carrot_market.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val seller: String,
    val address: String,
): Parcelable