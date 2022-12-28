package com.example.week8

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// 데이터베이스 객체 연결
@Database(entities = [Memo::class], version = 2)
abstract class AppDatabase: RoomDatabase() {
    // dao 연결
    abstract fun memoDao(): MemoDao

    // 싱글톤 패턴 -> 전역적으로 사용
    companion object {
        // 데이터베이스가 실제로 담기는 객체
        private var appDatabase: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context):AppDatabase? {
            if (appDatabase == null) {
                synchronized(AppDatabase::class.java) {
                    appDatabase = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app-database"
                    ).allowMainThreadQueries().build()
                }
            }
            return appDatabase
        }
    }
}