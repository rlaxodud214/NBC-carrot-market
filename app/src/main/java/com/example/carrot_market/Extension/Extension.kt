package com.example.carrot_market.Extension

import android.widget.ImageView
import com.example.carrot_market.R

fun ImageView.setHeartIcon(likeActivate: Boolean) {
    setImageResource(
        if (likeActivate) {
            R.drawable.ic_heart_fill
        } else {
            R.drawable.ic_heart_gray
        }
    )
}
