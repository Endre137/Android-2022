package com.example.quizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation


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
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        startQuizButton = view.findViewById(R.id.start)
        startQuizButton.setOnClickListener(){
            Navigation.findNavController(view).navigate(R.id.action_HomeFragment_to_startFragment)
        }

//        questionsButton = view.findViewById(R.id.questions)

        profileButton = view.findViewById(R.id.profile)
        profileButton.setOnClickListener(){
            Navigation.findNavController(view).navigate(R.id.action_HomeFragment_to_profileFragment)
        }

        return view
    }

}


