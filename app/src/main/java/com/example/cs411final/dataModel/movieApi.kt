package com.example.cs411final.dataModel

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface movieApi{
    @GET("/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed")
    fun getMovies() : Call<movieList>

    @GET("/3/movie/{id}/videos?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed")
    fun getVideo(@Path("id") movieId : Int) : Call<videoList>
}