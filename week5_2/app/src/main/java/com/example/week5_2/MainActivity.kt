package com.example.week5_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week5_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var getResultText: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataList: ArrayList<Data> = arrayListOf()
        val dataRVAdapter = DataRVAdapter(dataList)

        binding.rvData.adapter = dataRVAdapter
        binding.rvData.layoutManager = LinearLayoutManager(this)

        // MemoActivity에서 돌아왔을 때 데이터를 전달 받기 위한 콜백 등록
        getResultText = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data = result.data?.getStringExtra("data")

                // 리사이클러뷰에 데이터 추가
                dataList.apply {
                    add(Data(data))
                }
                // 리사이클러뷰에 데이터가 추가됐음을 알림
                dataRVAdapter.notifyItemRangeInserted(dataRVAdapter.itemCount + 1, 1)
            }
        }

        // 추가 버튼 -> MemoActivity로 이동
        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, MemoActivity::class.java)
            getResultText.launch(intent)
        }
    }
}