package com.example.carrot_market.presentation

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.DialogInterface
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.KeyEvent
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
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

        initView()
        initRecyclerView()
        initfloatActionButton()
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

    private fun initView() {
        binding.ivNotification.setOnClickListener {
            checkPermission()
            showNotification()
        }
    }

    private fun initfloatActionButton() {
        with(binding) {
            fabToTop.setOnClickListener {
                rvPosts.smoothScrollToPosition(0)
            }
        }
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

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (!NotificationManagerCompat.from(this).areNotificationsEnabled()) {
                // 알림 권한이 없다면, 사용자에게 권한 요청
                val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                    putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
                }
                startActivity(intent)
            }
        }
    }

    private fun showNotification() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val builder: NotificationCompat.Builder

        // 26 버전 이상
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "one-channel"
            val channelName = "My Channel One"

            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            // 채널에 다양한 정보 설정
            channel.apply {
                description = "My Channel One Description"
                setShowBadge(true)
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)
                enableVibration(true)
            }

            // 채널을 NotificationManager에 등록
            manager.createNotificationChannel(channel)

            // 채널을 이용하여 builder 생성
            builder = NotificationCompat.Builder(this, channelId)
        } else { // 26 버전 이하
            builder = NotificationCompat.Builder(this)
        }

        // 알림의 기본 정보
        builder.run {
            setSmallIcon(R.mipmap.ic_launcher)
            setWhen(System.currentTimeMillis())
            setContentTitle("키워드 알림")
            setContentText("설정한 키워드에 대한 알림이 도착했습니다!!")
        }

        manager.notify(11, builder.build())
    }
}