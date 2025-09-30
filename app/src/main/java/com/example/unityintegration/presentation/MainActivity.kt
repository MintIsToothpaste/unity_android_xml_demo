package com.example.unityintegration.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.unityintegration.databinding.ActivityMainBinding
import com.unity3d.player.UnityPlayerActivity

/**
 * 메인 Activity: XML 레이아웃과 ViewModel을 얻어 Unity 의치 실행과 메시지 전달매점을 관리합니다.
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ViewModel state 관찰
        viewModel.unityState.observe(this, Observer { state ->
            binding.textStatus.text = state
        })

        // Unity 실행 버튼
        binding.btnStartUnity.setOnClickListener {
            viewModel.launchUnityFeature()
            val intent = Intent(this, UnityHostActivity::class.java)
            startActivity(intent)
        }

        // Unity로 메시지 보내기
        binding.btnSendToUnity.setOnClickListener {
            viewModel.sendToUnity()
        }
    }
}
