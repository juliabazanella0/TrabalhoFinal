package com.example.trabalho_final

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_ask_continue.view.*

class AskContinueFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ask_continue, container, false)

        view.btNextQuestion.setOnClickListener { goToQuestionFragment() }
        view.btFinishGame.setOnClickListener { goToGameOverFragment() }
        return view
    }

    fun saveGame(){

    }

    fun obterToken(){

    }

    fun endGame(){

    }

    fun goToQuestionFragment(){
        findNavController().navigate(R.id.action_askContinueFragment_to_questionFragment)
    }

    fun goToGameOverFragment(){
        findNavController().navigate(R.id.action_askContinueFragment_to_gameOverFragment)
    }

}