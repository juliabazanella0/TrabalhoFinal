package com.example.trabalho_final.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.trabalho_final.database.daos.PersonDAO
import com.example.trabalho_final.models.Person

@Database(entities = [Person::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun personDao(): PersonDAO
}