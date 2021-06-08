package com.example.trabalho_final

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_game_over.view.*

class GameOverFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_game_over, container, false)

        view.btContinue.setOnClickListener { goToConfigGameFragment() }

        return view
    }

    fun getGame(){

    }

    fun goToConfigGameFragment(){
        findNavController().navigate(R.id.action_gameOverFragment_to_configGameFragment)
    }

}