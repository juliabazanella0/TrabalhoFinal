package com.example.trabalho_final.models

data class Difficulty(
        var name: String,
        var value: String
) {
    override fun toString() = name
}