package com.example.week9

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.week9.databinding.ActivityLoginBinding

import com.kakao.auth.ApiErrorCode
import com.kakao.auth.ISessionCallback
import com.kakao.auth.Session
import com.kakao.network.ErrorResult
import com.kakao.usermgmt.UserManagement
import com.kakao.usermgmt.callback.MeV2ResponseCallback
import com.kakao.usermgmt.response.MeV2Response
import com.kakao.util.exception.KakaoException

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var callback: SessionCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        callback = SessionCallback()
        Session.getCurrentSession().addCallback(callback)
        Session.getCurrentSession().checkAndImplicitOpen()

        binding.btnKakao.setOnClickListener {
            callback = SessionCallback()
            Session.getCurrentSession().addCallback(callback)
            Session.getCurrentSession().checkAndImplicitOpen()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        if (Session.getCurrentSession().handleActivityResult(requestCode, resultCode, data))
        {
            Log.e(TAG, "onActivityResult()에서 세션 획득!!")
            return
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    override fun onDestroy()
    {
        super.onDestroy()
        Session.getCurrentSession().removeCallback(callback)
    }

    inner class SessionCallback : ISessionCallback {
        override fun onSessionOpenFailed(exception: KakaoException?) {
            Log.e("Log", "Session Call back :: onSessionOpenFailed: ${exception?.message}")
        }

        override fun onSessionOpened() {
            UserManagement.getInstance().me(object : MeV2ResponseCallback() {

                override fun onFailure(errorResult: ErrorResult?) {
                    val result = errorResult?.errorCode
                    if (result == ApiErrorCode.CLIENT_ERROR_CODE) {
                        Toast.makeText(
                            this@LoginActivity,
                            "네트워크 연결이 불안정합니다. 다시 시도해 주세요",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "로그인 도중 오류가 발생했습니다 : ${errorResult?.errorMessage}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onSessionClosed(errorResult: ErrorResult?) {
                    Toast.makeText(this@LoginActivity, "세션이 닫혔습니다. 다시 시도해 주세요", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onSuccess(result: MeV2Response?) {
                    Log.e("카카오 로그인", "결과 : $result")
                    Log.e("카카오 로그인", "아이디 : ${result!!.id}")
                    Log.e("카카오 로그인", "이메일 : ${result.kakaoAccount.email}")
                    Log.e("카카오 로그인", "닉네임 : ${result.kakaoAccount.profile.nickname}")

                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    intent.putExtra("email", result.kakaoAccount.email)
                    intent.putExtra("nickname", result.kakaoAccount.profile.nickname)
                    startActivity(intent)
                    finish()
                }
            })
        }
    }
}

/*
        // retrofit 객체
        val retrofit = Retrofit.Builder()
            .baseUrl("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // retrofit 객체를 interface와 연결
        val apiService = retrofit.create(ApiService::class.java)

        // 연결
        apiService.getCheckEmail("abc@abc.com").enqueue(object: Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                if (response.isSuccessful) {
                    val responseData = response.body()

                    if (responseData != null) {
                        Log.d("Retrofit", "Response\nCode: ${responseData.code} Message: ${responseData.msg}")
                    }
                } else {
                    Log.w("Retrofit", "Response Not Successful ${response.code()}")
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                Log.e("Retrofit", "Error!", t)
            }
        })
*/
