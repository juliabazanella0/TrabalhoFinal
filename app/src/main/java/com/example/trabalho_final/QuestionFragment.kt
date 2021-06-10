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
    lateinit var answerAdapter: AnswerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_question, container, false)

        mostrarPoblem(view)

        view.btAnswer.setOnClickListener {
            answer()
        }

        return view
    }

    fun obterToken(): String{
        val token = requireContext()
                .getSharedPreferences("login", Context.MODE_PRIVATE)
                .getString("token", "")

        return token!!
    }

    fun openProblem() {}

    fun answer(){
        val dao = AnswerDAO()
        val selectedAnswer = answerAdapter.getAnswer()
        if(selectedAnswer == null) {
            return
        }

        dao.answer(obterToken(), selectedAnswer.order) { response ->
            val isCorrect = response.data.answer.correctAnswer.order == selectedAnswer.order
            val score = response.data.answer.score

            val bundle = Bundle()
            bundle.putLong("score", score)
            bundle.putBoolean("isCorrect", isCorrect)
            findNavController().navigate(R.id.askContinueFragment, bundle)
            //goToAskContinueFragment()
        }

    }

    fun mostrarPoblem(view: View){
        val dao = ProblemDAO()
        dao.next(obterToken()){ response ->
            idQuestion.setText(Html.fromHtml(response.data.problem.question).toString())
            answerAdapter = AnswerAdapter(mutableListOf())
            view.listAnswer.adapter = answerAdapter
            view.listAnswer.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            answerAdapter.update(response.data.problem.answers.toMutableList())
        }
    }

//    fun goToAskContinueFragment(){
//        findNavController().navigate(R.id.askContinueFragment)
//    }
}
