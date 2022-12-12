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
import androidx.navigation.Navigation


class QuestionFragment : Fragment() {
    val TAG = "Answer"
    private lateinit var answerOption: RadioGroup
    private lateinit var nextButton: Button
    private lateinit var questionText: TextView
    private lateinit var radioButton: RadioButton
    private val viewModel: QuizViewModel by activityViewModels()


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_2, container, false)
        val button: Button = view.findViewById(R.id.nextButton)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.apply {
            initViewItems(this)
            addRadioButton()
            registerListeners(this)


        }


    }
    fun onBackPressed() {
        Toast.makeText(activity, "Disabled Back Press", Toast.LENGTH_SHORT).show()
    }

    private fun addRadioButton() {

        for (i in 0..viewModel.getAnswerNumber() - 1) {
            val radioButton = RadioButton(activity)

            radioButton.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            radioButton.id = i
            radioButton.setText(viewModel.getAnswer(i))
            answerOption.addView(radioButton)
        }

    }


    private fun registerListeners(view: View) {

        nextButton.setOnClickListener {
            val id: Int = answerOption.checkedRadioButtonId

            if (id != -1) {
                val selectedOption: Int = answerOption.checkedRadioButtonId

                radioButton = view.findViewById(selectedOption)
                val answer = radioButton.text
                if (!viewModel.isFinalQuestion()) {

                    Log.i(TAG, "${viewModel.getCorrectAnswer()} ---  $answer ---${radioButton.id} ")

                    if ((answer) == viewModel.getCorrectAnswer()) {
                        viewModel.incrementCorrectAnswers()

                    }
                    viewModel.incrementCounter()
                    Navigation.findNavController(view).navigate(R.id.action_fragment2_self)
                } else {

                    Log.i(TAG, "${viewModel.getCorrectAnswer()} ---  $answer")

                    if ((answer) == viewModel.getCorrectAnswer()) {
                        viewModel.incrementCorrectAnswers()
                        Log.i(TAG, "${viewModel.getCorrectAnswer()}")
                    }
                    viewModel.incrementCounter()
                    Navigation.findNavController(view).navigate(R.id.action_fragment2_to_fragment3)
                }
            } else {
                Toast.makeText(activity, "Nothing selected!", Toast.LENGTH_SHORT).show()
            }

        }

    }

    private fun initViewItems(view: View) {

        nextButton = view.findViewById(R.id.nextButton)
        answerOption = view.findViewById(R.id.radioGroup)
        questionText = requireView().findViewById(R.id.questionText)
        questionText.text = viewModel.getQuestion()
    }
}