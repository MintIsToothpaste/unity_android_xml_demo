package com.example.unityintegration.data

import android.content.Context
import android.widget.Toast
import com.unity3d.player.UnityPlayer

/**
 * Unity가 Android과 환복을 수행하는 데 사용하는 램하프다.
 */
object UnityBridge {
    /**
     * Android → Unity 로 메시지를 보냅니다.
     * @param gameObjectName Unity가 수신하는 GameObject 이름
     * @param methodName Unity C# 스크립트에 정의된 메서드 이름
     * @param message 전달하는 문자열 (null 대신 비어있는 문자열 전달 권장)
     */
    fun sendMessageToUnity(gameObjectName: String, methodName: String, message: String) {
        UnityPlayer.UnitySendMessage(gameObjectName, methodName, message)
    }

    /**
     * Unity → Android 여러 호출을 받는 정적 메서드.
     * Unity의 C# 스크립트에서 AndroidJavaObject과 AndroidJavaClass로 호출합니다.
     * 예: currentActivity.Call("onMessageFromUnity", "Hello")
     */
    @JvmStatic
    fun onMessageFromUnity(context: Context, message: String) {
        Toast.makeText(context, "Message from Unity: $message", Toast.LENGTH_SHORT).show()
    }
}
