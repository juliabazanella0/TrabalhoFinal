package com.example.trabalho_final

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController

class QuestionFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_question, container, false)
    }

    //implementar pergunta e resposta

    fun goToAskContinueFragment(){
        findNavController().navigate(R.id.action_questionFragment_to_askContinueFragment)
    }

}
