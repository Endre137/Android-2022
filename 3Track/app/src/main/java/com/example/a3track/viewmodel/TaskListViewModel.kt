package com.example.a3track.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.a3track.MyApplication
import com.example.a3track.model.Task
import com.example.a3track.repository.TrackerRepository
import kotlinx.coroutines.launch



class TaskListViewModelFactory(
    private val repository: TrackerRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskListViewModel( repository) as T
    }
}

data class AllMyTasks(
    var tasks : MutableList<Task> = mutableListOf()
)

class TaskListViewModel(val repository: TrackerRepository) : ViewModel() {
//    var taskList = MutableLiveData<MutableList<Task>>()
//    var tasks : MutableList<Task> = mutableListOf()

    private val _uistate = MutableLiveData(AllMyTasks())

    fun getTasks():MutableList<Task>{
        return _uistate.value!!.tasks
    }

    fun readTasks(){
        viewModelScope.launch {
            try{
                Log.i("yyy", "alma")
                val response = repository.getTasks(MyApplication.token)
                if(response.isSuccessful){
//                    taskList.value = response.body()

                    val responses = response.body().toString().trim().split("),")
                    Log.i("responses", responses[0].toString())

                    for(i in responses){
                        val currentTask = i.split(",")
                        val tId = currentTask[0].split("=")[1]
                        val tTitle = currentTask[1].split("=")[1]
                        val tDescription = currentTask[2].split("=")[1]
                        val tCreated_time = currentTask[3].split("=")[1]
                        val tCreated_by = currentTask[4].split("=")[1].toLong()
                        val tAssigned_to_user = currentTask[5].split("=")[1].toLong()
                        val tPriority = currentTask[6].split("=")[1].toInt()
                        val tDeadLine = currentTask[7].split("=")[1].toLong()
                        val tDepartment = currentTask[8].split("=")[1].toInt()
                        val tStatus = currentTask[9].split("=")[1].toInt()


                        val tProgress: String?
                        if (i == responses.last()){
                            tProgress = currentTask[10].split("=")[1].dropLast(2)
                        }else{
                            tProgress = currentTask[10].split("=")[1]
                        }


                        val task1 = Task(tId.toLong(),tTitle,tDescription,tCreated_time.toLong(),tCreated_by,tAssigned_to_user,tPriority,tDeadLine,tDepartment,tStatus,tProgress.toInt())
                        _uistate.value?.tasks!!.add(task1)
                        Log.i("yyy", "banan")
//                        tasks.add(Task(tId,tTitle,tDescription,tCreated_time,tCreated_by,tAssigned_to_user,tPriority,tDeadLine,tDepartment,tStatus.toInt()))
//                        Log.i("Responses", tasks.toString())
//                        Log.i("aaa", "fut")
                    }

                }else{
                    Log.i("y", response.message())
                }
            }catch(e: Exception){
                Log.i("yy", e.toString())
            }
        }
    }

}