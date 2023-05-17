package com.example.cauroselapp

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=c3cd540cc8c144f2a59e3436b3dfa9d0

const val baseUrl = "https://newsapi.org/"
const val apiKey = "c3cd540cc8c144f2a59e3436b3dfa9d0"

interface Interface {

    @GET("v2/top-headlines?apiKey=$apiKey")
    fun newsToday(@Query("page") page:Int, @Query("country")country:String):Call<News>

}

object newsObject{
    val instance: Interface
    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        instance = retrofit.create(Interface::class.java)
    }
}