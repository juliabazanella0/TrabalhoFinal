package com.example.trabalho_final.network.services

import com.example.trabalho_final.models.responses.GameResponse
import retrofit2.Call
import retrofit2.http.*

interface GameService {
    //obter
    @GET("games")
    fun start(
            @Header("Authorization") token: String,        //necessita da autenticação, dificuldade e categoria para iniciar o jogo
            @Query("difficulty") difficulty: String,
            @Query("id_category") category: String?
    ): Call<GameResponse>

    @DELETE("games")
    fun endgame(@Header("Authorization") token: String): Call<GameResponse>
}