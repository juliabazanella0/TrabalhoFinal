package com.example.trabalhqo_final.dao

import com.example.trabalho_final.models.responses.CategoryResponse
import com.example.trabalho_final.network.services.CategoryService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryDAO {

    val url = "https://super-trivia-server.herokuapp.com/"
    val retrofit = Retrofit.Builder().baseUrl("https://super-trivia-server.herokuapp.com").addConverterFactory(GsonConverterFactory.create()).build()

    val service = retrofit.create(CategoryService::class.java)

    fun getAll(finished: (response: CategoryResponse) -> Unit) {
        return service.getCategories().enqueue(object: Callback<CategoryResponse> {
            override fun onResponse(                //voltando com "sucesso" do server
                    call: Call<CategoryResponse>,
                    response: Response<CategoryResponse>
            ) {
                response?.body()?.let { response ->
                    finished(response)
                }
            }

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {            //voltando com falha do server(erro conexão)
                //erro conexão
            }

        })
    }

}