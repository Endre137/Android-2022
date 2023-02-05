package com.example.a3track.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a3track.MyApplication
import com.example.a3track.R
import com.example.a3track.tasks.TaskAdapter
import com.example.a3track.viewmodel.AllUserViewModel
import com.example.a3track.viewmodel.TaskListViewModel


class MyTasksFragment : Fragment() {
    private val taskListViewModel: TaskListViewModel by activityViewModels()
    private  val allUserViewModel: AllUserViewModel by activityViewModels()
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<TaskAdapter.ViewHolder>? = null
    lateinit var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//         val factory = TaskListViewModelFactory(TrackerRepository())

        //taskListViewModel = ViewModelProvider(this, factory).get(TaskListViewModel::class.java)
        allUserViewModel.getAllUsers(MyApplication.token)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        taskListViewModel.readTasks()

        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_tasks, container, false)

        Log.i("taskfragment1", taskListViewModel.getTasks().toString());

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView =view.findViewById(R.id.recycleViewTasks)
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager

        adapter = TaskAdapter(taskListViewModel.getTasks(),object : TaskAdapter.OnItemClickListener {
            override fun onItemClick(position: Int,taskId : Int) {
                replaceFragment(TaskDetailsFragment(taskId))
            }
        })
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter
        Log.i("taskfragment2", taskListViewModel.getTasks().toString());
//        recyclerView = view.findViewById(R.id.recycleViewTasks)
//        initViewItems()


//        taskListViewModel.taskList.observe(viewLifecycleOwner){
//            val taskList = taskListViewModel.taskList.value
//            Log.i("yyy-task", taskList?.get(0)?.title.toString())
//
//
//        }

    }
    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = requireFragmentManager()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container,fragment)
        fragmentTransaction.commit()
    }
}