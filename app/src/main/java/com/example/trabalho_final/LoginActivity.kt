package com.example.trabalho_final

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.trabalho_final.database.AppDatabase
import com.example.trabalho_final.database.daos.PersonDAO
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(){
    private lateinit var dao: PersonDAO

    fun createDB() {
        // Create DB instance
        val db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "person-db"
        )
            .allowMainThreadQueries()
            .build()

        // Get DAO
        dao = db.personDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(isLoggedIn()){
            iniciarMain()
            return
        }

        setContentView(R.layout.activity_login)
        createDB()

        btLogin.setOnClickListener{

            val email = txtEmail.text.trim().toString()
            val password = txtSenha.text.trim().toString()
            val person = dao.findByName(email, password)

            if (person != null) {

                val sharedPref = getSharedPreferences("login", Context.MODE_PRIVATE)
                with (sharedPref.edit()) {
                    person?.id?.let { it1 -> putLong("personId", it1) }
                    commit()
                }
                val highScore = sharedPref.getLong("personId", 0)

                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }
            else{
                Toast.makeText(this, "Dados Inválidos", Toast.LENGTH_LONG).show();
            }

        }
        tvRegister.setOnClickListener{
            val intent = Intent( this, RegisterActivity::class.java)
            startActivity(intent)

        }

    }

    private fun isLoggedIn(): Boolean{
        val id = this.getSharedPreferences("login", Context.MODE_PRIVATE)
            .getLong("personId", 0)

        return !id.equals(0L)
    }

    private fun iniciarMain(){
        var intent = Intent( this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }

        startActivity(intent)
    }
}