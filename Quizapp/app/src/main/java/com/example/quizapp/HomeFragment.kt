package com.example.quizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button


class HomeFragment : Fragment() {
    private lateinit var startQuizButton : Button
    private lateinit var questionsButton : Button
    private lateinit var profileButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }

    private fun initViewItems(view: View) {

        startQuizButton = view.findViewById(R.id.start)
        questionsButton = view.findViewById(R.id.questions)
        profileButton = requireView().findViewById(R.id.profile)
    }
}