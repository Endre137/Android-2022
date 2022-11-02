package com.example.quizapp

import QuizViewModel
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation


class QuestionFragment : Fragment() {
    val TAG= "QuestionFragment"
    private lateinit var answerOption: RadioGroup
    private lateinit var nextButton: Button
    private lateinit var questionText : TextView
    private lateinit var radioButton1: RadioButton
    private lateinit var radioButton2: RadioButton
    private lateinit var radioButton3: RadioButton
    private lateinit var radioButton4: RadioButton
    private val viewModel: QuizViewModel by activityViewModels()

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
//        var answer: Int = answerOption.checkedRadioButtonId.toString().toInt()

        nextButton.setOnClickListener{

            var id: Int = answerOption.checkedRadioButtonId

//            Log.i(TAG, "$id")
//            Log.i(TAG, "${radioButton1.id}")
            if(id!=-1){

                var answer = id - radioButton1.id + 1
                if(!viewModel.isFinalQuestion()){
//                    Log.i(TAG, "$answer")


//                    Log.i(TAG, "${viewModel.getCorrectAnswer()}")
                    Log.i(TAG, "${viewModel.getCorrectAnswer() } ---  $answer")

                    if ((answer) == viewModel.getCorrectAnswer()){
                        viewModel.incrementCorrectAnswers()

                    }
                    viewModel.incrementCounter()
                    Navigation.findNavController(view).navigate(R.id.action_fragment2_self)
                }
                else {

                    Log.i(TAG, "${viewModel.getCorrectAnswer() } ---  $answer")

                    if ((answer) == viewModel.getCorrectAnswer()){
                        viewModel.incrementCorrectAnswers()
                        Log.i(TAG, "${viewModel.getCorrectAnswer()}")
                    }
                    viewModel.incrementCounter()
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
        radioButton1 = requireView().findViewById(R.id.radioButton)
        radioButton2 = requireView().findViewById(R.id.radioButton2)
        radioButton3 = requireView().findViewById(R.id.radioButton3)
        radioButton4 = requireView().findViewById(R.id.radioButton4)
        questionText = requireView().findViewById(R.id.questionText)
        questionText.text = viewModel.getQuestion()
        radioButton1.text = viewModel.getAnswer1()
        radioButton2.text = viewModel.getAnswer2()
        radioButton3.text = viewModel.getAnswer3()
        radioButton4.text = viewModel.getAnswer4()
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