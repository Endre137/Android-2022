package com.example.quizapp

import QuizViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels


class ProfileFragment : Fragment() {
    lateinit var playerName: TextView
    lateinit var highScore: TextView
    private val viewModel: QuizViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_profile, container, false)

        playerName = view.findViewById(R.id.player)
        playerName.text = viewModel.getUserName().toString()


        highScore = view.findViewById(R.id.highScore)
        highScore.text = viewModel.getHighestScore().toString()
        return view
    }

}