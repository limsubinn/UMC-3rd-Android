package com.example.week8

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week8.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataList: ArrayList<Memo> = arrayListOf()
        val dataRVAdapter = DataRVAdapter(dataList)

        binding.rvData.adapter = dataRVAdapter
        binding.rvData.layoutManager = LinearLayoutManager(this)

        val sharedPrefs = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()

        val roomDb = AppDatabase.getInstance(this)

        if (roomDb != null) {
            // 메모 가져오기 -> 리사이클러뷰에 추가
            val memoList = roomDb.memoDao().selectAll()

            if (memoList.isNotEmpty()) {
                dataList.addAll(memoList)
            }

            dataRVAdapter.notifyDataSetChanged()
        }

        // 리사이클러뷰 아이템 클릭 이벤트
        dataRVAdapter.setItemClickListener( object : DataRVAdapter.ItemClickListener{
            // 아이템 클릭 -> 삭제
            override fun onItemClick(view: View, position: Int) {
                val temp = dataList[position]
                roomDb?.memoDao()?.delete(temp)

                dataList.removeAt(position)
                dataRVAdapter.notifyDataSetChanged()
            }

            override fun onFavoriteClick(view: View, position: Int) {
                val temp = dataList[position].toString()
                val tempId = roomDb?.memoDao()?.selectByMemo(temp)?.memoId.toString()

                editor.putString(tempId, temp)
                editor.apply()

                roomDb?.memoDao()?.updateHeartByMemo(temp, 1)
            }
        })

        // 추가 버튼 -> MemoActivity로 이동
        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, MemoActivity::class.java)
            startActivity(intent)
        }

        // 즐겨찾기 버튼 -> FavoriteActivity로 이동
        binding.btnFavorite.setOnClickListener {
            val intent = Intent(this, FavoriteActivity::class.java)
            startActivity(intent)
        }
    }
}
