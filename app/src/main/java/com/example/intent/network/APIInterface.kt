package com.example.intent.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
interface APIInterface {

    @GET("search/repositories")
    fun searchRepo(@Query("q") name: String): Call<GitHubSearchModel?>
}