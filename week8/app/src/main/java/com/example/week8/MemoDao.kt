package com.example.week8

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MemoDao {
    @Insert
    fun insert(memo: Memo)

    @Delete
    fun delete(memo: Memo)

    @Query("SELECT * FROM Memo")
    fun selectAll(): List<Memo>

    @Query("SELECT * FROM Memo WHERE memo = :memo")
    fun selectByMemo(memo: String): Memo

    @Query("UPDATE Memo SET heart = :heart WHERE memo = :memo")
    fun updateHeartByMemo(memo: String, heart: Int)
}