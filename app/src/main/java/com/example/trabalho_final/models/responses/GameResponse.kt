package com.example.trabalho_final.models.responses

import com.example.trabalho_final.models.Game

data class GameResponse(
        var status: String,
        var data: ResponseData
) {
    data class ResponseData(
        var game: Game
    )
}