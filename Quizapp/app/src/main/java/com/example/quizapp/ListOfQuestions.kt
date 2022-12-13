package com.example.quizapp

import QuizViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ListOfQuestions : Fragment() {
    private val itemsList = ArrayList<String>()
    private lateinit var customAdapter: CustomAdapter
    private val viewModel: QuizViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list_of_questions, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        customAdapter = CustomAdapter(itemsList)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = customAdapter
        prepareItems()
        return view
    }
    private fun prepareItems() {

        for(i in 0 ..  viewModel.getquestionsNumber()-1) {
            itemsList.add(viewModel.getQuestion2(i))
        }
        customAdapter.notifyDataSetChanged()
    }
}