package com.example.week8

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week8.databinding.ActivityFavoriteBinding

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val favoriteList: ArrayList<Memo> = arrayListOf()
        val favoriteRVAdapter = FavoriteRVAdapter(favoriteList)

        binding.rvData.adapter = favoriteRVAdapter
        binding.rvData.layoutManager = LinearLayoutManager(this)

        val roomDb = AppDatabase.getInstance(this)
        val sharedPrefs = getSharedPreferences("sharedprefs", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()

        if (roomDb != null) {
            // 메모 가져오기 -> 리사이클러뷰에 추가
            val memoList = roomDb.memoDao().selectAll()

            favoriteList.clear()

//            for (m in memoList) {
//                val saved = sharedPrefs.getString(m.toString(), "")
//                val memo = saved?.let { Memo(it) }
//
//                if (memo != null) {
//                    favoriteList.add(memo)
//                }
//            }

            favoriteRVAdapter.notifyDataSetChanged()
        }

        // 리사이클러뷰 아이템 클릭 이벤트
        favoriteRVAdapter.setItemClickListener( object : FavoriteRVAdapter.ItemClickListener{
            override fun onDeleteClick(view: View, position: Int) {
                val temp = favoriteList[position].toString()
                val tempId = roomDb?.memoDao()?.selectByMemo(temp)?.memoId.toString()

                editor.remove(tempId)
                editor.apply()
            }
        })

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            finish()
        }
    }
}