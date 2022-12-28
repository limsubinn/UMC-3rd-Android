package com.example.week5_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week5_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val dataList: ArrayList<Data> = arrayListOf()

        dataList.apply {
            add(Data("hello", "1"))
            add(Data("hello", "2"))
            add(Data("hello", "3"))
            add(Data("hello", "4"))
            add(Data("hello", "5"))
            add(Data("hello", "6"))
            add(Data("hello", "7"))
            add(Data("hello", "8"))
            add(Data("hello", "9"))
            add(Data("hello", "10"))
            add(Data("hello", "11"))
            add(Data("hello", "12"))
            add(Data("hello", "13"))
            add(Data("hello", "14"))
            add(Data("hello", "15"))
            add(Data("hello", "16"))
            add(Data("hello", "17"))
            add(Data("hello", "18"))
            add(Data("hello", "19"))
            add(Data("hello", "20"))
            add(Data("hello", "21"))
            add(Data("hello", "22"))
            add(Data("hello", "23"))
            add(Data("hello", "24"))
            add(Data("hello", "25"))
            add(Data("hello", "26"))
            add(Data("hello", "27"))
            add(Data("hello", "28"))
            add(Data("hello", "29"))
            add(Data("hello", "30"))
        }

        val dataRVAdapter = DataRVAdapter()

        viewBinding.rvData.adapter = dataRVAdapter
        viewBinding.rvData.layoutManager = LinearLayoutManager(this)

        /*
        Handler(mainLooper).postDelayed({
            dataList.apply {
                add(Data("hello", "16"))
                add(Data("hello", "17"))
                add(Data("hello", "18"))
                add(Data("hello", "19"))
            }
            // dataRVAdapter.notifyDataSetChanged()
            dataRVAdapter.notifyItemRangeInserted(15, 4)
        }, 1000)
        */
    }
}