package com.example.quizapp

import QuizViewModel
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation

class QuizEndFragment : Fragment() {
    val TAG="QuizEndFragment"
    lateinit var nameText: TextView
    lateinit var result: TextView
    private val viewModel: QuizViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "${viewModel.getScore()}")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_3, container, false)

        nameText = view.findViewById(R.id.nameText)
        nameText.text = viewModel.getUserName().toString()


        result = view.findViewById(R.id.result)
        result.text = "${viewModel.getScore()}/${viewModel.getQuestionCounter()-1}"


        val button: Button = view.findViewById(R.id.finishButton)
        button.setOnClickListener{

            Navigation.findNavController(view).navigate(R.id.action_fragment3_to_fragment1)
            viewModel.resetCounter()
        }
        return view
    }

}