package com.example.trabalho_final.dao

import com.example.trabalho_final.models.responses.AnswerResponse
import com.example.trabalho_final.network.services.PeopleService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AnswerDAO {
    val url = "https://super-trivia-server.herokuapp.com/"
    val retrofit = Retrofit.Builder().baseUrl("https://super-trivia-server.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
    val service = retrofit.create(PeopleService::class.java)

    /**fun answer(
            token: String,
            answer: Long,
            finished: (response: AnswerResponse) -> Unit
    ) {
        return service.answer(token, answer).enqueue(object: Callback<AnswerResponse> {
            override fun onResponse(call: Call<AnswerResponse>, response: Response<AnswerResponse>) {
                response?.body()?.let { response ->
                    finished(response)
                }
            }

            override fun onFailure(call: Call<AnswerResponse>, t: Throwable) {
                //erro
            }
        })
    }**/
}