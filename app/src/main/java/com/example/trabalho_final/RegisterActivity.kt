package com.example.trabalho_final

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trabalho_final.dao.PersonDAO
import com.example.trabalho_final.models.InPerson
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.view.*

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

    private fun validaSenha(password: String, confirmPassword: String): HashMap<String, String> {
        val errorsMap = HashMap<String, String>()
        if(password.length < 6) {
            resources?.let {
                errorsMap.put("password",
                        it.getString(R.string.is_too_short, it.getString(R.string.password))
                )
            }
        }

        if(!password.equals(confirmPassword)) {
                errorsMap.put("confirmPassword",
                    resources.getString(R.string.dont_match, resources.getString(R.string.confirm_password), resources.getString(R.string.password))
                )
        }
        return errorsMap
    }

    fun register(name: String, email: String, password: String, confirmPassword: String) {
        val passwordErrors = validaSenha(password, confirmPassword)
        if(passwordErrors.isNotEmpty()) {
            if(passwordErrors["password"] != null) {
                    errorPassword.apply {
                    visibility = View.VISIBLE
                    errorPassword.text = passwordErrors["password"]
                }
            }
            if(passwordErrors["confirmPassword"] != null) {
                    errorConfirmPassword.apply {
                    visibility = View.VISIBLE
                    text = passwordErrors["confirmPassword"]
                }

            }

            return
        }

        dao.register(InPerson.Person(name, email, password, ""), {
            response ->
                Log.d("newUser", response.toString())
                goToLogin()
        }, {
            error ->

            if(error.data.errors.name!!.isNotEmpty()) {
                    errorName.apply {
                    text = getErrorString(resources.getString(R.string.name), error.data.errors.name!!.first()).toString()
                    visibility = View.VISIBLE
                }
            }
            if(error.data.errors.email!!.isNotEmpty()) {
                    errorEmail.apply {
                    text = getErrorString(resources.getString(R.string.email), error.data.errors.email!!.first()).toString()
                    visibility = View.VISIBLE
                }
            }
            if(error.data.errors.password!!.isNotEmpty()) {
                    errorPassword.apply {
                    text = getErrorString(resources.getString(R.string.password), error.data.errors.password!!.first()).toString()
                    visibility = View.VISIBLE
                }
            }
        })

    }

    private fun getErrorString(fieldName: String, error: String): String? {
        return when {
            Regex("can\'t be blank").containsMatchIn(error) -> resources?.getString(R.string.cant_be_blank, fieldName)
            Regex("has already been taken").containsMatchIn(error) -> resources?.getString(R.string.has_already_been_taken, fieldName)
            Regex("is too short").containsMatchIn(error) -> resources?.getString(R.string.is_too_short, fieldName)
            Regex("is invalid").containsMatchIn(error) -> resources?.getString(R.string.is_invalid, fieldName)

            else -> null
        }
    }

    fun goToLogin() {
        var intent = Intent( this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }

        startActivity(intent)
    }
}