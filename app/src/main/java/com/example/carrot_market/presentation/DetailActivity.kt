package com.example.carrot_market.presentation

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.carrot_market.R
import com.example.carrot_market.Util
import com.example.carrot_market.data.Post
import com.example.carrot_market.data.PostDataSource
import com.example.carrot_market.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

class DetailActivity : AppCompatActivity() {
    private val binding: ActivityDetailBinding by lazy {
        ActivityDetailBinding.inflate(layoutInflater)
    }

    private lateinit var post: Post
    private var position: Int = Int.MIN_VALUE

    private val posts = PostDataSource.dummyData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initData()
        initView()
    }

    private fun initData() {
//        if (intent.hasExtra("post")) {
//            post = intent.getParcelableExtra<Post>("post")!!
//        }

        if (intent.hasExtra("post_position")) {
            position = intent.getIntExtra("post_position", Int.MIN_VALUE)
            post = posts[position]
        }
    }

    private fun initView() = with(binding) {
        icBack.setOnClickListener {
            finish()
        }

        ivLike.setOnClickListener {
            val (preLikeActivate, currentLikeActivate) = posts[position].isLikeActivate to !posts[position].isLikeActivate

            posts[position] = posts[position].copy(
                isLikeActivate = !preLikeActivate
            )

            switchHeartIcon(currentLikeActivate)

            val actionText = if (currentLikeActivate) "에 추가" else "에서 삭제"
            it.showSnackbar(actionText)
        }

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

    private fun View.showSnackbar(actionText: String) {
        Snackbar.make(this, "관심 목록${actionText}되었습니다.", Snackbar.LENGTH_LONG).let {
            setSnackBarOption(it)
            it.anchorView = binding.ivLike
            it.show()
        }
    }

    private fun switchHeartIcon(likeActivate: Boolean) {
        binding.ivLike.setImageResource(
            if (likeActivate) {
                R.drawable.ic_heart_fill
            } else {
                R.drawable.ic_heart_gray
            }
        )
    }

    // 스낵바 옵션 설정
    @SuppressLint("ResourceAsColor")
    private fun setSnackBarOption(snackBar: Snackbar) = with(snackBar) {
        animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE // 애니메이션 설정
        setTextColor(Color.WHITE) // 안내 텍스트 색 지정
        setBackgroundTint(getColor(R.color.gray_deep_dark)) // 백그라운드 컬러 지정
    }
}