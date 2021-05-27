package com.example.trabalho_final

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trabalho_final.dao.PersonDAO
import com.example.trabalho_final.models.Login
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(){
    private val dao: PersonDAO = PersonDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(isLoggedIn()){
            iniciarMain()
            return
        }

        setContentView(R.layout.activity_login)

        btLogin.setOnClickListener{

            val email = txtEmail.text.trim().toString()
            val password = txtSenha.text.trim().toString()
            login(email, password)
        }
        tvRegister.setOnClickListener{
            val intent = Intent( this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    private fun login(email: String, password: String) {
        dao.login(Login(email, password), { response ->
            //Log.d("loginResponse", response.toString())
            val sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE)
            val user = response.data?.user
            with(sharedPref.edit()) {
                putString("name", user?.name)
                putString("email", user?.email)
                putString("token", user?.token)

                commit()
            }

            iniciarMain()
        },
        {
            error ->

            errorLogin.apply {
                text = resources.getString(R.string.invalid_email_or_password)
                visibility = View.VISIBLE
            }
            //Log.d("loginResponse", error.toString())

        })
    }

    private fun isLoggedIn(): Boolean{
        val token = this.getSharedPreferences("login", Context.MODE_PRIVATE)
            .getString("token", "")

        return !token!!.isBlank()
    }

    private fun iniciarMain(){
        var intent = Intent( this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }

        startActivity(intent)
    }
}