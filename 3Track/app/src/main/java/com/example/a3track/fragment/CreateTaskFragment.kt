package com.example.a3track.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import com.example.a3track.R
import com.example.a3track.viewmodel.AllUserViewModel
import com.example.a3track.viewmodel.DepartmentViewModel
import com.example.a3track.viewmodel.TaskListViewModel
import com.example.a3track.viewmodel.UserViewModel

@Suppress("DEPRECATION")
class CreateTaskFragment : Fragment() {
    private val tasksVM: TaskListViewModel by activityViewModels()
    private val currentUserVM: UserViewModel by activityViewModels()
    private val departmentVM: DepartmentViewModel by activityViewModels()
    private val allUserVM: AllUserViewModel by activityViewModels()
    private lateinit var taskTitle: EditText
    private lateinit var taskDesc: EditText
    private lateinit var taskAssignee: Spinner
    private lateinit var taskPriority: Spinner
    private lateinit var taskDeadline: EditText
    private lateinit var taskDepartment: Spinner
    private var taskStatus: Int = 0
    private lateinit var submitButton: Button
    private lateinit var backButton: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_task, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewItems()
        registerListeners()
    }

    private fun registerListeners() {
        val adapter = context?.let { ArrayAdapter(it,android.R.layout.simple_spinner_item,allUserVM.getAllUserArray()) }
        adapter!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        taskAssignee.adapter = adapter

        val adapterPriority = context?.let { ArrayAdapter(it,android.R.layout.simple_spinner_item,
            listOf("Low", "Medium", "High"))
        }
        adapterPriority!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        taskPriority.adapter = adapterPriority

        val adapterDepartment = context?.let { ArrayAdapter(it,android.R.layout.simple_spinner_item,departmentVM.getDepartmentsNames()) }
        adapterDepartment!!.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        taskDepartment.adapter = adapterDepartment

        backButton.setOnClickListener{
            replaceFragment(MyTasksFragment())
        }

        submitButton.setOnClickListener{
            context?.let { it1 -> tasksVM.createTask(it1,taskTitle.text.toString(),
                taskDesc.text.toString(),
                allUserVM.getIdByName(taskAssignee.selectedItem.toString().split(" ")[0],taskAssignee.selectedItem.toString().split(" ")[1]),
                getPriority(taskPriority.selectedItem.toString()),
                taskDeadline.text.toString().toLong(),
                departmentVM.getDepartmentIdByName(taskDepartment.selectedItem.toString()),
                0
            ) }
            tasksVM.updateTasks(taskTitle.text.toString(),
                taskDesc.text.toString(),
                allUserVM.getIdByName(taskAssignee.selectedItem.toString().split(" ")[0],taskAssignee.selectedItem.toString().split(" ")[1]),
                getPriority(taskPriority.selectedItem.toString()),
                taskDeadline.text.toString().toLong(),
                departmentVM.getDepartmentIdByName(taskDepartment.selectedItem.toString()),
                0,1)
            replaceFragment(MyTasksFragment())
        }
    }

    private fun initViewItems() {
        taskTitle = requireView().findViewById(R.id.ediTextTaskName)
        taskDesc = requireView().findViewById(R.id.editTextDescription)
        taskAssignee = requireView().findViewById(R.id.spinnerAsignee)
        taskPriority = requireView().findViewById(R.id.spinnerPriority)
        taskDeadline = requireView().findViewById(R.id.editTextDeadline)
        taskDepartment = requireView().findViewById(R.id.spinnerDepartment)
        submitButton = requireView().findViewById(R.id.createTaskBurron)
        backButton = requireView().findViewById(R.id.backButtonNewTask)
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentManager = requireFragmentManager()
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.container,fragment)
        fragmentTransaction.commit()
    }

    fun getPriority(priorityText: String):Int{
        if(priorityText == "Low"){
            return 1
        }

        if(priorityText == "Medium"){
            return 2
        }

        if(priorityText == "High"){
            return 3
        }
        return 0

    }

}