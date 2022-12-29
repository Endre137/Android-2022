package com.example.a3track.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
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

class TaskListViewModel(val repository: TrackerRepository) : ViewModel() {
    var taskList = MutableLiveData<List<Task>>()

    fun readTasks(){
        viewModelScope.launch {
            try{
                val response = repository.getTasks(MyApplication.token)
                if(response.isSuccessful){
                    taskList.value = response.body()
                }else{
                    Log.i("yyy", response.message())
                }
            }catch(e: Exception){
                Log.i("yyy", e.toString())
            }
        }
    }
}