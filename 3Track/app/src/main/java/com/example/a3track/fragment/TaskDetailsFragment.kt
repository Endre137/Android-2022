package com.example.a3track.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.a3track.R
import com.example.a3track.viewmodel.AllUserViewModel
import com.example.a3track.viewmodel.TaskListViewModel
import java.text.SimpleDateFormat


@Suppress("DEPRECATION")
class TaskDetailsFragment(taskId: Int) : Fragment() {
    private val taskID = taskId
    private lateinit var taskTitle: TextView
    private lateinit var groupName: TextView
    private lateinit var assignedBy: TextView
    private lateinit var assignedDate: TextView
    private lateinit var assignee: TextView
    private lateinit var progress: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var deadline: TextView
    private lateinit var priority: TextView
    private lateinit var description: TextView
    private lateinit var backButton: ImageView
    private val tasksVM : TaskListViewModel by activityViewModels()
    private val usersVM: AllUserViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_task_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewItems()
        registerListeners()
    }
    private fun registerListeners() {

        backButton.setOnClickListener {
            replaceFragment(MyTasksFragment())
        }
    }

    private fun initViewItems(){
        val task = tasksVM.getTaskById(taskID)
        val dateformat2 = SimpleDateFormat("MMMM dd yyyy")
        taskTitle = requireView().findViewById(R.id.taskTitleDet)
        taskTitle.text = task!!.title
        groupName = requireView().findViewById(R.id.departmentNameDet)
//        groupName.text = groupsVM.getGroupById(task.department_ID)
        groupName.text = "department"
        assignedBy = requireView().findViewById(R.id.assignedByDet)
        assignedBy.text = usersVM.getName(task.created_by_user_ID.toInt())
        Log.i("cret", usersVM.getName(task.created_by_user_ID.toInt()).toString())
        assignedDate = requireView().findViewById(R.id.assignedDateDet)
        assignedDate.text = dateformat2.format(task.created_by_user_ID)
        assignee = requireView().findViewById(R.id.assigneeDet)
        assignee.text = usersVM.getName(task.asigned_to_user_ID.toInt())
        progress = requireView().findViewById(R.id.percenDoneDet)
        progress.text = task.progress.toString()
        progressBar = requireView().findViewById(R.id.progressBar2)
        if(task.progress == null){
            task.progress=1
        }
        progressBar.progress = task.progress!!
        deadline = requireView().findViewById(R.id.deadlineDateDet)
        deadline.text = dateformat2.format(task.deadline)
        priority = requireView().findViewById(R.id.priorityTextDet)
        when(task.priority){
            1->{
                priority.text = "Low priority"
            }

            2->{
                priority.text = "Medium priority"
            }

            3->{
                priority.text = "High priority"
            }
        }
        description = requireView().findViewById(R.id.descriptonDet)
        description.text= task.description
        backButton = requireView().findViewById(R.id.backButton)
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = requireFragmentManager()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container,fragment)
        fragmentTransaction.commit()
    }
}