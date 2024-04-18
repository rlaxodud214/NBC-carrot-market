package com.example.carrot_market.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SalesPost(
    val post: Post,
    val user: User,
    val product: Product,
): Parcelable