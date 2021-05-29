package com.example.trabalho_final.models.responses

import com.example.trabalho_final.models.Ranking

data class RankingResponse(
    var status: String,
    var data: ResponseData
) {
    data class ResponseData(
        var ranking: List<Ranking>
    )
}
