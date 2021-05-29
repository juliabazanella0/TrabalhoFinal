package com.example.trabalho_final.models

class InPerson {
    data class Person(
        var name: String,
        var email: String,
        var password: String,
        var token: String
) {
        var id: Long? = null
  }
}