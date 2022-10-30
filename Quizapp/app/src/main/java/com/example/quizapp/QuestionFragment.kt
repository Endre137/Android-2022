package com.example.quizapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation


class QuestionFragment : Fragment() {
    private lateinit var answerOption: RadioGroup
    private lateinit var nextButton: Button
    private val viewModel: QuizViewModel by viewModels()

    companion object {
         var fragmentCounter : Int = 0

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_2, container, false)
        val button: Button = view.findViewById(R.id.nextButton)
        button.setOnClickListener{

        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            initViewItems(this)
            registerListeners(this)
        }
    }

    private fun registerListeners(view: View){
        nextButton.setOnClickListener{
            var id: Int = answerOption.checkedRadioButtonId
            if(id!=-1){
                if(fragmentCounter < 4){
                    fragmentCounter++
                    Navigation.findNavController(view).navigate(R.id.action_fragment2_self)
                }
                else {
                    fragmentCounter = 0
                    Navigation.findNavController(view).navigate(R.id.action_fragment2_to_fragment3)
                }
            }
            else{
                Toast.makeText(activity,"Nothing selected!", Toast.LENGTH_SHORT).show()
            }

        }
        answerOption
    }

    private fun initViewItems(view: View){
        nextButton = view.findViewById(R.id.nextButton)
        answerOption = view.findViewById(R.id.radioGroup)
    }


    fun onRadioButtonClicked(view: View) {
        if (view is RadioButton) {
            // Is the button now checked?
            val checked = view.isChecked

            // Check which radio button was clicked
//            when (view.getId()) {
//                R.id.radio_pirates ->
//                    if (checked) {
//                        // Pirates are the best
//                    }
//                R.id.radio_ninjas ->
//                    if (checked) {
//                        // Ninjas rule
//                    }
//            }
        }
    }
}