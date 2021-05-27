package com.example.trabalho_final.dao

import android.util.Log
import com.example.trabalho_final.models.*
import com.example.trabalho_final.models.erros.LoginError
import com.example.trabalho_final.models.erros.RegisterError
import com.example.trabalho_final.network.services.PeopleService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.trabalho_final.models.erros.Error

class PersonDAO {

    val url = "https://super-trivia-server.herokuapp.com/"
    val retrofit = Retrofit.Builder().baseUrl("https://super-trivia-server.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build()
    val service = retrofit.create(PeopleService::class.java)

    fun getAll(){
        service.getAll().enqueue(object : Callback<List<InPerson.Person>> {
            override fun onFailure(call: Call<List<InPerson.Person>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<InPerson.Person>>, response: Response<List<InPerson.Person>>) {
                val people = response.body()!!
            }

        })
    }

    fun login(login: Login, finished: (login: LoginResponse) -> Unit, fail: (response: LoginError?) -> Unit)  {
        service.auth(login).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
              if (response.isSuccessful){
                  val loginResponse = response.body()!!
                  finished(loginResponse)
              } else {
                  val errorResponse = Error.toPOJO(retrofit, LoginError::class.java, response.errorBody()!!)
                  fail(errorResponse)
              }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e("falhouu", t.toString())
            }
        })
    }

    fun register(person: InPerson.Person, finished: (person: OutPerson) -> Unit, fail: (errorResponse: RegisterError) -> Unit) {
        service.insert(person).enqueue(object : Callback<OutPerson> {
            override fun onResponse(call: Call<OutPerson>, response: Response<OutPerson>) {
                if(response.isSuccessful) {
                    val registeredUser = response.body()!!
                    finished(registeredUser)
                    return
                }
                val error = Error.toPOJO(retrofit, RegisterError::class.java, response.errorBody()!!)

                fail(error!!)
            }
            override fun onFailure(call: Call<OutPerson>, t: Throwable) {
                Log.e("falhouu", t.toString())
            }
        })
    }
}