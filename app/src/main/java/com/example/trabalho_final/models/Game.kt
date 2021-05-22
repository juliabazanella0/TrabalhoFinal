package com.example.trabalho_final.models

import com.google.gson.annotations.SerializedName
import java.util.Date

data class Game(
    var creation: String,
    var status: String,
    @SerializedName("started_at") var startedAt: Date,
    @SerializedName("finished_at") var finishedAt: Date,
    var score: Long
)


//{
//    "status": "success",
//    "data": {
//    "game": {
//    "creation": "new_game",
//    "status": "playing",
//    "started_at": "2021-05-15T03:19:56.171Z",
//    "score": 0
//}
//}
//}