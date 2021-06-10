package com.example.trabalho_final

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.trabalho_final.dao.GameDAO
import kotlinx.android.synthetic.main.fragment_ask_continue.view.*
import java.util.*

class AskContinueFragment : Fragment() {
    private val gameDao = GameDAO()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_ask_continue, container, false)
        val isCorrect = arguments?.getBoolean("isCorrect")
        val score = arguments?.getLong("score")

        view.lbCorrect.text = getString(
                if(isCorrect!!) R.string.correct_answer
                    else R.string.incorrect_answer)

        view.scoree.text = getString(R.string.score, score)

        view.btNextQuestion.setOnClickListener { goToQuestionFragment() }
        view.btFinishGame.setOnClickListener { goToGameOverFragment() }

        return view
    }

    fun goToQuestionFragment(){
        findNavController().navigate(R.id.action_askContinueFragment_to_questionFragment)
    }

    fun goToGameOverFragment(){
        findNavController().navigate(R.id.action_askContinueFragment_to_gameOverFragment)
    }

}