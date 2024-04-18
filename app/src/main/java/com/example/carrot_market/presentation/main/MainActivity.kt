package com.example.carrot_market.presentation.main

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
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.carrot_market.R
import com.example.carrot_market.data.source.PostDataSource
import com.example.carrot_market.databinding.ActivityMainBinding
import com.example.carrot_market.presentation.detail.DetailActivity
import com.example.carrot_market.presentation.dialog.ExitConfirmAlertDialogFragment
import com.example.carrot_market.presentation.dialog.RemoveItemConfirmAlertDialogFragment

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var postAdapter: PostAdapter
    private var itemPositionByDetail = Int.MIN_VALUE

    // TODO : 외부로 빼기, check: context에 종속성 있음
    private val exitConfirmDialog by lazy {
        ExitConfirmAlertDialogFragment() { _, _ ->
            finish()
        }.show(supportFragmentManager, ExitConfirmAlertDialogFragment.TAG)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
        initRecyclerView()
        initfloatActionButton()
    }

    override fun onRestart() {
        super.onRestart()

        postAdapter.notifyItemChanged(itemPositionByDetail)
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
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            exitConfirmDialog
            true
        } else {
            false
        }
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
        val onPostClickListener: (Int) -> Unit = { position ->
            runDetailActivity(position)
        }

        val onPostLongClickListener: (Int) -> Boolean = { position ->
            showDialogForItemRemove(position)
            true
        }

        postAdapter = PostAdapter(
            onClick = onPostClickListener,
            onLongClick = onPostLongClickListener,
        ).apply {
            posts = PostDataSource.dummyData
        }

        val dividerItemDecoration = DividerItemDecoration(applicationContext, VERTICAL)

        with(binding.rvPosts) {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(dividerItemDecoration) // item 간의 구분선 추가를 위함
            initAnimation()
        }
    }

    private fun RecyclerView.initAnimation() = with(this) {
        addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val currentPosition = layoutManager.findFirstVisibleItemPosition()

                if (currentPosition == 0) {
                    binding.fabToTop.hide()
                } else {
                    binding.fabToTop.show()
                }
            }
        })
    }

    private fun runDetailActivity(position: Int) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("post_position", position)
        itemPositionByDetail = position

        startActivity(intent)
    }

    private fun showDialogForItemRemove(position: Int): Boolean {
        RemoveItemConfirmAlertDialogFragment() {  _, _ ->
            PostDataSource.dummyData.removeAt(position)
            postAdapter.notifyItemRemoved(position)
        }.show(supportFragmentManager, RemoveItemConfirmAlertDialogFragment.TAG)

        return true
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

        // Android 8.0(26 버전) 이상부터는 NotificationChannel을 생성해야함
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                packageName,                             // 다른 App과의 구분을 위함
                "키워드 알림",                        // 이 앱의 여러 알림 중 선택적으로 활성화를 할 때,
                                                          // 사용자에게 표시되는 알림 채널의 이름
                NotificationManager.IMPORTANCE_DEFAULT   // 알림 중요도 설정값
            )

            // 채널에 다양한 정보 설정
            channel.apply {
                // description : 개별 알림 on/off시 알림 채널에 대한 설명 메시지
                description = "\$user님이 설정하신 키워드에 대한 알림을 제공합니다."
                // badge : 앱 아이콘에 알림 개수가 보이게 해주는 기능
                setShowBadge(true)
                enableVibration(true)

                // 알림 시 소리 설정
                val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audioAttributes = AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .setUsage(AudioAttributes.USAGE_ALARM)
                    .build()
                setSound(uri, audioAttributes)
            }

            // 채널을 NotificationManager에 등록
            manager.createNotificationChannel(channel)

            // 채널을 이용하여 builder 생성F
            builder = NotificationCompat.Builder(this, packageName)
        } else { // 26 버전 이하
            builder = NotificationCompat.Builder(this)
        }

        // 알림의 기본 정보
        builder.run {
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle("키워드 알림")
            setContentText("설정한 키워드에 대한 알림이 도착했습니다!!")

            // (선택) 시간 지정이 필요할 때 사용
            // setWhen(System.currentTimeMillis())

            // (선택) (긴 텍스트나 사진을) 확장해서 볼 수 있게 해줌
            // setStyle(NotificationCompat.BigTextStyle().bigText("~~")
            // setStyle(NotificationCompat.BigPictureStyle().bigPicture(~~~))
        }

        // Builder.build() : Notification 객체를 반환함
        val myNotificationID = 1
        manager.notify(myNotificationID, builder.build())
    }
}