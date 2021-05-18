package com.example.trabalho_final.models

data class Login (
    var email: String,
    var password: String
)

data class DataLogin(
    var user: InPerson.Person
)

data class LoginResponse(
    var status: String,
    var data: DataLogin?
)