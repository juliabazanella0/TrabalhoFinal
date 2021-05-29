package com.example.trabalho_final.models.responses

import com.example.trabalho_final.models.Problem

data class ProblemResponse(
    var status: String,
    var data: ResponseData
) {
    data class ResponseData(
        var problem: Problem
    )
}