package com.example.trabalho_final.network.services

import com.example.trabalho_final.models.responses.RankingResponse
import retrofit2.Call
import retrofit2.http.GET

interface RankingService {
    @GET("ranking")
    fun getAll(): Call<RankingResponse>
}