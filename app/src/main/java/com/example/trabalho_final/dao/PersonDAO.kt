package com.example.trabalho_final.dao

import com.example.trabalho_final.models.*
import com.example.trabalho_final.network.services.PeopleService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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

    fun login(login: Login, finished: (login: LoginResponse) -> Unit, fail: (response: PersonError?) -> Unit)  {
        service.auth(login).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
              if (response.body() != null){
                  val loginResponse = response.body()!!
                  finished(loginResponse)
              } else {
                  val response = PersonError("error", "data")
                  fail(response)
              }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                // Erro de conexão
            }
        })
    }

    fun register(person: InPerson.Person, finished: (person: OutPerson) -> Unit, fail: (response: PersonError?) -> Unit) {
        service.insert(person).enqueue(object : Callback<OutPerson> {
            override fun onResponse(call: Call<OutPerson>, response: Response<OutPerson>) {
                if(response.body() != null) {                       //|| password.length>=6
                    val registeredUser = response.body()!!
                    finished(registeredUser)

                } else {
                    val response = PersonError("fail", "Connection Error") //erro
                    fail(response)
                }
            }
            override fun onFailure(call: Call<OutPerson>, t: Throwable) {
                fail(PersonError("fail", "Connection Error"))
                //Log.e
            }
        })
    }
}