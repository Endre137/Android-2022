package com.example.quizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation


class QuizStartFragment : Fragment() {
    lateinit var startButton : Button
    lateinit var nameText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            initViewItems(this)
            registerListeners(view)
        }
    }

    private fun registerListeners(view: View){
        if(nameText.text.isEmpty()){
            val toast = Toast.makeText(context, "The text field is empty",Toast.LENGTH_SHORT)
            toast.show()
        }
        else {
            startButton.setOnClickListener {
                Navigation.findNavController(view).navigate(R.id.action_fragment1_to_fragment2)
            }
        }
    }


    private fun initViewItems(view: View){
        startButton = view.findViewById(R.id.startButton)
        nameText = view.findViewById(R.id.nameText)
    }

}