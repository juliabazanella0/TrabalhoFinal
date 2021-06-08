package com.example.trabalho_final.adapters

import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trabalho_final.R
import com.example.trabalho_final.models.Answer
import kotlinx.android.synthetic.main.fragment_question.*
import kotlinx.android.synthetic.main.item_answer.view.*

class AnswerAdapter(var answers: MutableList<Answer>): RecyclerView.Adapter<AnswerAdapter.ViewHolder>() {
    private var selectedAnswer: Answer? = null
    private var selectedView: View? = null
    //private var answers = listOf<Answer>()

    init {
        update(answers)
    }

    override fun getItemCount() = answers.size                   //retorna total de elementos na lista

    fun getAnswer(): Answer? { return this.selectedAnswer }

    fun update(answers: MutableList<Answer>){              //notificar que a lista de resposta foi atualizada
        this.answers = answers
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_answer, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val answer = answers[position]
        holder.fillView(answer)
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun fillView(answer: Answer) {
            //itemView.rbAnswer.setText(answer.description)
            itemView.rbAnswer.setText(Html.fromHtml(answer.description).toString())

            itemView.rbAnswer.setOnClickListener { view ->
                if(selectedAnswer != null) {
                    if(!selectedAnswer!!.equals(answer)) {
                        selectedView?.rbAnswer?.isChecked = false
                    }
                }
                selectedAnswer = answer
                selectedView = view
            }
        }
    }
}