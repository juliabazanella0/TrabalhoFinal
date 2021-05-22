package com.example.trabalho_final.network.services

import com.example.trabalho_final.models.responses.CategoryResponse
import retrofit2.Call
import retrofit2.http.GET

interface CategoryService {

    //retorno
    @GET("categories")
    fun getCategories(): Call<CategoryResponse>

}