package com.example.week9

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("getVilageFcst")
    fun getVilageFcst(
        @Query("serviceKey") serviceKey : String,
        @Query("pageNo") pageNo : String,
        @Query("numOfRows") numOfRows : String,
        @Query("dataType") dataType : String,
        @Query("base_date") base_date : String,
        @Query("base_time") base_time : String,
        @Query("nx") nx : String,
        @Query("ny") ny : String
    ) : Call<VilageFcstResponse>
}