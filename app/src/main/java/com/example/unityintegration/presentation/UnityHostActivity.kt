package com.example.unityintegration.presentation

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.unityintegration.R
import com.unity3d.player.UnityPlayerActivity

class UnityHostActivity : UnityPlayerActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // super 가 이미 setContentView(mUnityPlayer) 완료

        // 1) 오버레이 뷰(우상단 고정) 생성
        val overlay = LinearLayoutCompat(this).apply {
            orientation = LinearLayoutCompat.VERTICAL
            background = AppCompatResources.getDrawable(
                this@UnityHostActivity, R.drawable.overlay_background
            )
            setPadding(16, 16, 16, 16)

            addView(AppCompatButton(context).apply {
                text = "Action 1"
                setOnClickListener { /* UnitySendMessage(...) */ }
            })
            addView(AppCompatButton(context).apply {
                text = "Action 2"
                setOnClickListener { /* ... */ }
            })
            addView(AppCompatTextView(context).apply {
                text = "상태: 대기중"
            })
        }

        // 2) 현재 ContentView는 FrameLayout(root) 이므로 FrameLayout.LayoutParams + gravity 사용
        val lp = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.WRAP_CONTENT,
            FrameLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.TOP or Gravity.END
            marginEnd = 16
            topMargin = 16
        }

        // 3) Unity 루트를 건드리지 말고, 오버레이만 추가
        addContentView(overlay, lp)
    }
}