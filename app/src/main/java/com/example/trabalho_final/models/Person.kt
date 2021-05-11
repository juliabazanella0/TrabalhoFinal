package com.example.trabalho_final.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "people")
data class Person (
    var name: String,
    var email: String,
    var password: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null
}