package com.example.trabalho_final

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.NavController
import androidx.navigation.Navigation

class AskContinueFragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ask_continue, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.btFinishGame).setOnClickListener(this)
        view.findViewById<Button>(R.id.btNextQuestion).setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.btFinishGame -> navController!!.navigate(R.id.action_askContinueFragment_to_gameOverFragment)
            R.id.btNextQuestion -> navController!!.navigate(R.id.action_askContinueFragment_to_questionFragment)
        }
    }

}