package com.khagani.posttask.service

import com.khagani.posttask.model.PostsModel
import retrofit2.Call
import retrofit2.http.GET

interface PostsAPI {
    @GET("/posts")
    fun getData(): Call<List<PostsModel>>
}