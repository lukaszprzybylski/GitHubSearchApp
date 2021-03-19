package com.example.intent.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.intent.network.GitHubSearchModel
import com.example.intent.constants.Constant
import com.example.intent.network.APIInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListViewModel : ViewModel() {

    val results: MutableLiveData<GitHubSearchModel> = MutableLiveData()
    val responseStatus = MutableLiveData<Boolean>().apply { value = true }

    fun searchByName(movieName: String) {
        val retrofit = Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(APIInterface::class.java)
        val call = service.searchRepo(movieName)
        call.enqueue(object : Callback<GitHubSearchModel?> {
            override fun onResponse(
                    call: Call<GitHubSearchModel?>,
                    response: Response<GitHubSearchModel?>
            ) {
                if (response.code() == 200) {
                    response.body().let {
                        results.value = response.body()
                        responseStatus.value = true
                    }
                } else {
                    responseStatus.value = false
                }
            }

            override fun onFailure(call: Call<GitHubSearchModel?>, t: Throwable) {
                Log.e("ListViewModel", "Error: " + t.message)
                results.value = null
                responseStatus.value = false
            }
        })
    }
}