package com.example.week9

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.week9.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // FirstActivity에서 넘어온 intent에 담겨있는 객체
        var bundle = intent.extras

        if (bundle != null) {
            // FirstActivity로부터 받은 텍스트를 text2에 설정
            val kakao_email = bundle.getString("email", "")
            val kakao_nickname = bundle.getString("nickname", "")

            binding.tvEmail.text = kakao_email
            binding.tvNickname.text = kakao_nickname
        }

        // gson 객체
        val gson : Gson = GsonBuilder()
            .setLenient()
            .create()

        // retrofit 객체
        var retrofit = Retrofit.Builder()
            .baseUrl("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        // retrofit 객체를 interface와 연결
        var apiService = retrofit.create(ApiService::class.java)

        var key_vilageFcst = "NQEXn61VkJYSRVIbg/6/3zEXlGfj0zdcDjwxAgwEzIOReIsO8T9hveilB5LL3WQl79Q+QaMxfRhCRRuYXEqy4Q=="

        // 연결
        apiService.getVilageFcst(key_vilageFcst, "1", "12", "JSON", "20221207", "0500", "61", "130").enqueue(object: Callback<VilageFcstResponse> {
            override fun onResponse(
                call: Call<VilageFcstResponse>,
                response: retrofit2.Response<VilageFcstResponse>
            ) {
                Log.d("Retrofit", "success")

                val result = response.body()

                if (result != null) {
                    for(i in result.response.body.items.item){
                        Log.d("Retrofit", "$i")
                    }
                }
            }

            override fun onFailure(call: Call<VilageFcstResponse>, t: Throwable) {
                Log.w("Retrofit", "fail")
                t.printStackTrace();

            }
        })

    }
}


//override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
//    if (response.isSuccessful) {
//        val responseData = response.body()
//
//        if (responseData != null) {
//            Log.d("Retrofit", "Response\nCode: ${responseData.code} Message: ${responseData.msg}")
//        }
//    } else {
//        Log.w("Retrofit", "Response Not Successful ${response.code()}")
//    }
//}
//
//override fun onFailure(call: Call<Response>, t: Throwable) {
//    Log.e("Retrofit", "Error!", t)
//}