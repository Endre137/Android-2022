package com.example.a3track.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.a3track.MyApplication
import com.example.a3track.model.CreateTaskRequest
import com.example.a3track.model.Task
import com.example.a3track.model.UpdateTaskRequest
import com.example.a3track.repository.TrackerRepository
import kotlinx.coroutines.launch



class TaskListViewModelFactory(
    private val repository: TrackerRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskListViewModel() as T
    }
}

data class AllMyTasks(
    var tasks : MutableList<Task> = mutableListOf()
)

class TaskListViewModel() : ViewModel() {
    //    var taskList = MutableLiveData<MutableList<Task>>()
//    var tasks : MutableList<Task> = mutableListOf()
    val repository = TrackerRepository()
    private var _uistate = MutableLiveData(AllMyTasks())

    fun getTasks(): MutableList<Task> {
        return _uistate.value!!.tasks
    }

    fun getTaskById(id: Int): Task? {
        for (t in _uistate.value!!.tasks) {
            if (t!!.ID == id.toLong()) {
                return t
            }
        }
        return null
    }

    fun resetTaskList() {
        for (i in _uistate.value!!.tasks) {
            _uistate.value!!.tasks.remove(i)
        }
    }

    fun createTask(
        context: Context,
        title: String,
        description: String,
        userId: Int,
        priority: Int,
        deadline: Long,
        departmentId: Int,
        status: Int
    ) {
        viewModelScope.launch {
            try {
                val response = repository.createTask(
                    MyApplication.token,
                    CreateTaskRequest(
                        title,
                        description,
                        userId,
                        priority,
                        deadline,
                        departmentId,
                        status
                    )
                )
                if (response!!.isSuccessful) {
                    Toast.makeText(context, "Created task", Toast.LENGTH_SHORT).show()
                }
            } catch (ex: Exception) {
                Log.i("exCreateTask", ex.message.toString())
            }
        }
    }
    var taskToUpdate = 0
    fun updateTasks(
        title: String,
        description: String,
        userId: Int,
        priority: Int,
        deadline: Long,
        departmentId: Int,
        status: Int,
        progress: Int
    ) {
        viewModelScope.launch {
            try {
                val response = repository.updateTask(
                    MyApplication.token,
                    UpdateTaskRequest(
                        taskToUpdate,
                        title,
                        description,
                        userId,
                        priority,
                        deadline,
                        departmentId,
                        status,
                        progress
                    )
                )
                if (response!!.isSuccessful) {
                    Log.i("updateTask", "successful")
                }
            } catch (ex: Exception) {
                Log.i("exUpdateTask", ex.message.toString())
            }
        }
    }

    var tId = 0
        fun readTasks() {
//        resetTaskList()
            viewModelScope.launch {
                try {
//                _uistate.value!!.tasks= mutableListOf()
                    Log.i("yyy", "alma")
                    val response = repository.getTasks(MyApplication.token)
                    Log.i("yyy", "alma2")
                    if (response.isSuccessful) {
//                    taskList.value = response.body()

                        val responses = response.body().toString().trim().split("),")
                        Log.i("responses", responses[0])

                        for (i in responses) {
                            val currentTask = i.split(",")
                            tId = currentTask[0].split("=")[1].toInt()
                            val tTitle = currentTask[1].split("=")[1]
                            val tDescription = currentTask[2].split("=")[1]
                            val tCreated_time = currentTask[3].split("=")[1]
                            val tCreated_by = currentTask[4].split("=")[1].toInt()
                            val tAssigned_to_user = currentTask[5].split("=")[1].toInt()
                            val tPriority = currentTask[6].split("=")[1].toInt()
                            val tDeadLine = currentTask[7].split("=")[1].toLong()
                            val tDepartment = currentTask[8].split("=")[1].toInt()
                            val tStatus = currentTask[9].split("=")[1].toInt()


                            val tProgress: String?
                            if (i == responses.last()) {
                                tProgress = currentTask[10].split("=")[1].dropLast(2)
                            } else {
                                tProgress = currentTask[10].split("=")[1]
                            }


                            val task1 = tProgress.toInt().let {
                                Task(
                                    tId.toLong(),
                                    tTitle,
                                    tDescription,
                                    tCreated_time.toLong(),
                                    tCreated_by,
                                    tAssigned_to_user,
                                    tPriority,
                                    tDeadLine,
                                    tDepartment,
                                    tStatus,
                                    it
                                )
                            }
                            Log.i("progress", task1.progress.toString())
                            if (task1.progress == null) {
                                Log.i("null", "itt vagyok")
                                task1.progress = 1
                            }
                            _uistate.value?.tasks!!.add(task1)
                            Log.i("yyy", "banan")
//                        Log.i("Responses", tasks.toString())
//                        Log.i("aaa", "fut")
                        }

                    } else {
                        Log.i("y", response.message())
                    }
                } catch (e: Exception) {
                    taskToUpdate = tId
                    Log.i("y2y", e.toString() + "   " + tId.toString())
                }
            }
        }
    }