package com.example.carrot_market.presentation.detail

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.carrot_market.util.setHeartIcon
import com.example.carrot_market.R
import com.example.carrot_market.Util
import com.example.carrot_market.data.model.SalesPost
import com.example.carrot_market.data.source.PostDataSource
import com.example.carrot_market.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private lateinit var salesPost: SalesPost
    private var position: Int = Int.MIN_VALUE

    private val salesPosts = PostDataSource.dummyData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getData()
        initView()
    }

    private fun getData() {
        if (intent.hasExtra(KEY_FOR_POSITION)) {
            position = intent.getIntExtra(KEY_FOR_POSITION, Int.MIN_VALUE)
            salesPost = salesPosts[position]
        }
    }

    private fun initView() = with(binding) {
        icBack.setOnClickListener {
            finish()
        }

        likeButtonClickListener()

        with(salesPost) {
            ivProductImage.setImageResource(product.image)
            ivUserProfileImage.setImageResource(R.drawable.img_user)
            tvUserName.text = user.seller
            tvUserAddress.text = user.address

            tvProductName.text = product.name
            tvProductContent.text = product.content
            tvProductPrice.text = Util.dec.format(product.price)
            ivLike.setHeartIcon(post.isLikeActivate)
        }
    }

    private fun likeButtonClickListener() = with(binding) {
        ivLike.setOnClickListener {
            val preLikeActivate = salesPosts[position].post.isLikeActivate
            val currentLikeActivate = !preLikeActivate

            salesPosts[position] = salesPosts[position].copy(
                post = salesPosts[position].post.copy(
                    isLikeActivate = !preLikeActivate
                )
            )

            ivLike.setHeartIcon(currentLikeActivate)

            val actionText = if (currentLikeActivate) "에 추가" else "에서 삭제"
            it.showSnackbar(actionText)
        }
    }

    private fun View.showSnackbar(actionText: String) {
        Snackbar.make(this, "관심 목록${actionText}되었습니다.", Snackbar.LENGTH_LONG).let {
            setSnackBarOption(it)
            it.anchorView = binding.ivLike
            it.show()
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun setSnackBarOption(snackBar: Snackbar) = with(snackBar) {
        animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE // 애니메이션 설정
        setTextColor(Color.WHITE) // 안내 텍스트 색 지정
        setBackgroundTint(getColor(R.color.gray_deep_dark)) // 백그라운드 컬러 지정
    }

    companion object {
        const val KEY_FOR_POSITION = "salesPost_position"
    }
}