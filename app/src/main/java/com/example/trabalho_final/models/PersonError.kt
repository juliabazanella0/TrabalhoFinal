package com.example.trabalho_final.models

data class PersonError(
    var status: String,
    var data: String
)

data class DataError(
    var error: Error
)

data class Error(
    var nome: List<String>?,
    var email: List<String>?,
    var password: List<String>?
)
