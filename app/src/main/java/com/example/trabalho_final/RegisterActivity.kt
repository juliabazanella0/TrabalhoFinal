package com.example.trabalho_final

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.trabalho_final.database.AppDatabase
import com.example.trabalho_final.database.daos.PersonDAO
import com.example.trabalho_final.models.Person
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
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
        setContentView(R.layout.activity_register)
        createDB()

        btnRegister.setOnClickListener {

            val name = editName.text.trim().toString()
            val email = editEmail.text.trim().toString()
            val password = editPassword.text.trim().toString()
            val confirmPassword = editCPassword.text.trim().toString()

            if (name.isNotEmpty() || email.isNotEmpty() || password.isNotEmpty() || confirmPassword.isNotEmpty()) {
                val person = Person(name, email, password)
                add(person)
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Dados para Registro Inválidos", Toast.LENGTH_LONG).show()
            }

//            if(!password.equals(confirmPassword)) {
//                Toast.makeText(this, "Senhas não coincidem", Toast.LENGTH_LONG).show()
//            }
        }
        tvLogin.setOnClickListener {
            finish()
        }
    }

    fun add(person: Person) {
        person.id = dao.insert(person)
    }
}