package com.example.cs411final

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofithelper {
    val baseUrl = "https://api.themoviedb.org/"

    fun getInstance():Retrofit{
        return Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).build()
    }
}