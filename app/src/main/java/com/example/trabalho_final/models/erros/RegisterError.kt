package com.example.trabalho_final.models.erros

data class RegisterError(
    var status: String,
    var data: Data
){
    data class Data(
            var errors: Errors
    ) {
        data class Errors(
            var nome: List<String>? = listOf(),
            var email: List<String>? = listOf(),
            var password: List<String>? = listOf()
        )
    }
}



