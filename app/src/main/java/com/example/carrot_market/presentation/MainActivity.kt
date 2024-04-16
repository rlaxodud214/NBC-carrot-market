package com.example.carrot_market.presentation

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.carrot_market.R
import com.example.carrot_market.adapter.PostAdapter
import com.example.carrot_market.data.Post
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
        val posts = PostDataSource.dummyData
        val postAdapter = PostAdapter(posts) { position ->
            onItemClickListener(posts[position])
        }

        val dividerItemDecoration = DividerItemDecoration(applicationContext, VERTICAL)

        with(binding.rvPosts) {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(dividerItemDecoration) // item 간의 구분선 추가를 위함
        }
    }

    private fun onItemClickListener(post: Post) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("post", post)

        startActivity(intent)
    }

    /**
     * 버튼이 눌렸을 때 호출됨
     *
     * return(Boolean) :
     *
     *     true : 이벤트를 처리한 경우
     *     false : 다음 수신자(onKey~~~)가 이벤트를 처리하도록 허용하는 경우
     *
     * ref: https://developer.android.com/reference/android/view/KeyEvent.Callback
     */
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        // 그 버튼이 BACK일 때
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            var builder = AlertDialog.Builder(this)

            val listener = object : DialogInterface.OnClickListener {
                override fun onClick(p0: DialogInterface?, p1: Int) {
                    if (p1 == DialogInterface.BUTTON_POSITIVE) {
                        finish()
                    }
                }
            }

            with(builder) {
                setIcon(R.drawable.ic_bubble_chat)
                setTitle("종료")
                setMessage("정말 종료하겠습니까?")
                setNegativeButton("취소", listener)
                setPositiveButton("확인", listener)
            }.show()
        }

        return true
    }
}