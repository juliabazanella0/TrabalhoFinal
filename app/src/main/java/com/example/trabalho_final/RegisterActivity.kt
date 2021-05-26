package com.example.trabalho_final

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trabalho_final.dao.PersonDAO
import com.example.trabalho_final.models.InPerson
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    private val dao: PersonDAO = PersonDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnRegister.setOnClickListener {
            val name = editName.text.trim().toString()
            val email = editEmail.text.trim().toString()
            val password = editPassword.text.trim().toString()
            val confirmPassword = editCPassword.text.trim().toString()

            register(name, email, password, confirmPassword)
        }
        tvLogin.setOnClickListener {
            finish()
        }
    }

    fun register(name: String, email: String, password: String, confirmPassword: String){

        if(!password.equals(confirmPassword) && password.length > 5) {
            Toast.makeText(this, "Senhas não coincidem", Toast.LENGTH_LONG).show()
            return //erro: registrando mesmo se a senhas estiverem diferentes

        }

        dao.register(InPerson.Person(name, email, password, ""), {
            response ->
            Log.d("newUser", response.toString())
            goToLogin()
        }, {
            error ->
            Toast.makeText(this, "Erro no Registro", Toast.LENGTH_LONG).show()
        })

    }

//            if(name.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank()) {
//                Toast.makeText(this, "Campos não podem ser vazios", Toast.LENGTH_LONG).show();
//            }


    fun goToLogin() {
        var intent = Intent( this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }

        startActivity(intent)
    }
}