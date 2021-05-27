package com.example.trabalho_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.fragment_config_game.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView.setupWithNavController(findNavController(R.id.navHostFragment))
    }


    fun goToLogin() {
        var intent = Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }

        startActivity(intent)
    }

    fun logout() {
        this.getSharedPreferences("game", MODE_PRIVATE)
                .edit().apply {
                    clear()
                    commit()
                }
        this.getSharedPreferences("config", MODE_PRIVATE)
                .edit().apply {
                    clear()
                    commit()
                }
        this.getSharedPreferences("login", MODE_PRIVATE)
                .edit().apply {
                    clear()
                    commit()
                }

        goToLogin()
    }
}