package com.example.quizapp

import QuizViewModel
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation


class QuizStartFragment : Fragment() {
    lateinit var startButton : Button
    lateinit var nameText: EditText
    private val viewModel: QuizViewModel by activityViewModels()

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

        startButton.setOnClickListener {
//            if(nameText.toString()){
            if(TextUtils.isEmpty(nameText.getText().toString())){
                val toast = Toast.makeText(activity, "The text field is empty",Toast.LENGTH_SHORT)
                toast.show()
            }
            else {
                viewModel.setUserName(nameText.text.toString())

                Navigation.findNavController(view).navigate(R.id.action_fragment1_to_fragment2)
            }
        }

    }


    private fun initViewItems(view: View){
        startButton = view.findViewById(R.id.startButton)
        nameText = view.findViewById(R.id.nameText)
    }

}