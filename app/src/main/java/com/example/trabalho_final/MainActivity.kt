package com.example.trabalho_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.fragment_config_game.*

class MainActivity : AppCompatActivity(), MenuItem.OnMenuItemClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView.setupWithNavController(findNavController(R.id.navHostFragment))
        bottomNavigationView.menu.getItem(1).setOnMenuItemClickListener(this)
    }

    fun goToLogin() {
        var intent = Intent(this, LoginActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }

        startActivity(intent)
    }

    fun logout() {
        this.getSharedPreferences("game", MODE_PRIVATE).edit().apply {
                    clear()
                    commit()
                }
        this.getSharedPreferences("config", MODE_PRIVATE).edit().apply {
                    clear()
                    commit()
                }
        this.getSharedPreferences("login", MODE_PRIVATE).edit().apply {
                    clear()
                    commit()
                }

        goToLogin()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if(item?.itemId == R.id.btLogout){
            logout()
        }
        return true
    }
}