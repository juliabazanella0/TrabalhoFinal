package com.example.trabalho_final.network.services

import com.example.trabalho_final.models.responses.ProblemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface ProblemService {                      //endpoints que necessitam do token de autenticação
    @GET("problems/view")
    fun view(@Header("Authorization") token: String): Call<ProblemResponse>

    @GET("problems/next")
    fun next(@Header("Authorization") token: String): Call<ProblemResponse>
}