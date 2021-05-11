package com.example.trabalho_final.database.daos

import androidx.room.*
import com.example.trabalho_final.models.Person

@Dao
interface PersonDAO {

    @Query(value = "SELECT * FROM people")
    fun getAll(): List<Person>

    @Query(value = "SELECT * FROM people WHERE id IN (:ids)")
    fun getAllByIds(ids: IntArray): List<Person>

    @Query(value = "SELECT * FROM people WHERE email = :email AND password = :password")
    fun findByName(email: String, password: String): Person

    @Insert
    fun insert(person: Person): Long

    @Insert
    fun insertAll(vararg people: Person): LongArray

    @Delete
    fun delete(person: Person)

    @Update
    fun update(person: Person)
}