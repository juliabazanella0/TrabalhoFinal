package com.example.trabalho_final.dao

import android.util.Log
import com.example.trabalho_final.models.responses.AnswerResponse
import com.example.trabalho_final.network.services.AnswerService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AnswerDAO {
    val retrofit = Retrofit.Builder().baseUrl("https://super-trivia-server.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
    val service = retrofit.create(AnswerService::class.java)

    fun answer(token: String, answer: Long, finished: (response: AnswerResponse) -> Unit
    ) {
        return service.answer(token, answer).enqueue(object: Callback<AnswerResponse> {
            override fun onResponse(call: Call<AnswerResponse>, response: Response<AnswerResponse>) {
                Log.d("xxx", "response")
                response?.body()?.let { response ->
                    finished(response)
                }
            }

            override fun onFailure(call: Call<AnswerResponse>, t: Throwable) {
                Log.d("xxx", "fail")
            }
        })
    }
}