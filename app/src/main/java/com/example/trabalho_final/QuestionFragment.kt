package com.example.trabalho_final

import android.content.Context
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trabalho_final.adapters.AnswerAdapter
import com.example.trabalho_final.dao.AnswerDAO
import com.example.trabalho_final.dao.ProblemDAO
import kotlinx.android.synthetic.main.fragment_config_game.view.*
import kotlinx.android.synthetic.main.fragment_question.*
import kotlinx.android.synthetic.main.fragment_question.view.*

class QuestionFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        mostrarPoblem(view)
        view.btAnswer.setOnClickListener {
            answer()
        }
        return view
    }

    fun getConfig(): Boolean {
        val categoryName = requireContext()
                .getSharedPreferences("config", Context.MODE_PRIVATE)
                .getString("name_category", "")

        val difficulty = requireContext()
                .getSharedPreferences("config", Context.MODE_PRIVATE)
                .getString("difficulty", "")

        return !categoryName!!.isEmpty() && !difficulty!!.isEmpty()
    }

    fun obterToken(): String{
        val token = requireContext()
                .getSharedPreferences("login", Context.MODE_PRIVATE)
                .getString("token", "")

        return token!!
    }

    fun openProblem() {}

    fun answer(){
//        val dao = AnswerDAO()
//        val answerAdapter = AnswerAdapter(mutableListOf())
//        val selectedAnswer = answerAdapter.getAnswer()
//        if(selectedAnswer == null) {
//            return
//        }
//
//        dao.answer(obterToken(), selectedAnswer.order){ response ->
//            val isCorrect = response.data.answer.correctAnswer.order == selectedAnswer.order
//            val score = response.data.answer.score
//        }
//
//        goToAskContinueFragment()
    }

    fun mostrarPoblem(view: View){
        val dao = ProblemDAO()
        dao.next(obterToken()){ response ->
            idQuestion.setText(Html.fromHtml(response.data.problem.question).toString())
            //idQuestion.setText(response.data.problem.question)
            val answerAdapter = AnswerAdapter(mutableListOf())
            view.listAnswer.adapter = answerAdapter
            view.listAnswer.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            answerAdapter.update(response.data.problem.answers.toMutableList())
        }
    }

    fun goToAskContinueFragment(){
        findNavController().navigate(R.id.askContinueFragment)
    }
}
