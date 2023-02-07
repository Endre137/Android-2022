package com.example.a3track.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.a3track.MyApplication
import androidx.lifecycle.viewModelScope
import com.example.a3track.model.GetCuResponse
import kotlinx.coroutines.launch
import com.example.a3track.model.User
import com.example.a3track.repository.TrackerRepository
import java.lang.Exception

class UserListViewModelFactory(
    private val repository: TrackerRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserListViewModel( ) as T
    }
}

class UserListViewModel() : ViewModel() {
    var userList = MutableLiveData<List<GetCuResponse>>()
    val repository= TrackerRepository()
    fun readUsers() {
//        Log.i("alma", userList.value.toString())
        viewModelScope.launch {
            try {
                val response = repository.getUsers(MyApplication.token)
                if(response.isSuccessful) {
                    userList.value = response.body()
                } else{
                    Log.i("xxx-uvm", response.message())
                }
            } catch (e: Exception) {
                Log.i("xxx", e.toString())
            }
        }
    }



}