package com.example.trabalho_final.models.responses

import com.example.trabalho_final.models.Answer
import com.google.gson.annotations.SerializedName

data class AnswerResponse(
    var status: String,
    var data: ResponseData
) {
    data class ResponseData(
        var answer: ResponseAnswer
    ) {
        data class ResponseAnswer(
                var status: String,
                @SerializedName("correct_answer")
                var correctAnswer: Answer,
                var score: Long
        )
    }
}