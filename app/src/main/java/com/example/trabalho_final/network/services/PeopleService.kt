package com.example.trabalho_final.network.services

import com.example.trabalho_final.models.*
import retrofit2.Call
import retrofit2.http.*

interface PeopleService {
    //obter todos
    @GET("people")
    fun getAll(): Call<List<InPerson.Person>>

    //registrar
    @POST("users")
    @Headers("Content-Type:application/json; charset=UTF-8")
    fun insert(@Body person: InPerson.Person): Call<OutPerson>

    //autenticar/login
    @POST("auth")
    @Headers("Content-Type: application/json; charset=UTF-8")
    fun auth(@Body login: Login): Call<LoginResponse>
}