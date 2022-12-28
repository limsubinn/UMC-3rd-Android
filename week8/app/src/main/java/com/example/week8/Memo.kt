package com.example.week8

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Memo(
    @ColumnInfo(name = "memo") val memo:String,
    @ColumnInfo(name = "heart") val heart:Int,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "memoId") val memoId: Int = 0
)
