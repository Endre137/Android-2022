package com.example.a3track.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.findNavController
import com.example.a3track.R
import com.example.a3track.repository.TrackerRepository
import com.example.a3track.viewmodel.TaskListViewModel
import com.example.a3track.viewmodel.TaskListViewModelFactory


class MyTasksFragment : Fragment() {
    private lateinit var taskListViewModel: TaskListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = TaskListViewModelFactory(TrackerRepository())
        Log.i("taskfragment", "megy");
        taskListViewModel = ViewModelProvider(this, factory).get(TaskListViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_my_tasks, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        taskListViewModel.readTasks()
        taskListViewModel.taskList.observe(viewLifecycleOwner){
            val taskList = taskListViewModel.taskList.value
            Log.i("yyy-task", taskList.toString())
        }
    }

}