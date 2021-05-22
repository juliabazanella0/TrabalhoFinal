package com.example.trabalho_final.models.responses

import com.example.trabalho_final.models.Category

data class CategoryResponse(
        var status: String,
        var data: ResponseData
    ) {

    data class ResponseData(
        var categories: List<Category>
    )
}
