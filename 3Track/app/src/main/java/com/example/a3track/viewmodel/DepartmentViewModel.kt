package com.example.a3track.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a3track.MyApplication
import com.example.a3track.model.Department
import com.example.a3track.model.GetCuResponse
import com.example.a3track.repository.TrackerRepository
import kotlinx.coroutines.launch

data class allDepartments(
    var department: MutableList<Department> = mutableListOf()
)

class DepartmentViewModel: ViewModel() {
    private val departments = MutableLiveData(allDepartments())
    val repository =  TrackerRepository()

    fun readDepartments(token: String){
        viewModelScope.launch {
            try {
                val response = repository.getDepartments(MyApplication.token)
                Log.i("depErr", response.toString())
                if (response.isSuccessful) {
                    val responses = response.body().toString().trim().split("),")
                    Log.i("depErr", responses.toString())
                    for(i in responses){
                        val currentResponse = i.split(",")
                        Log.i("depErr", currentResponse[0])
                        val tempId=currentResponse[0].split("=")[1].toInt()
                        var tempName = ""
                        if(i == responses.last()){
                             tempName=currentResponse[1].split("=")[1].dropLast(2)
                        }else{
                            tempName=currentResponse[1].split("=")[1]
                        }

                        Log.i("aaa",tempId.toString() +" "+ tempName)
                        departments.value?.department!!.add(Department(tempId,tempName))
                    }
                    departments.value?.department!!.last().name.dropLast(2)
                }

            }catch(ex: Exception){
                Log.i("departmenError", ex.message.toString())
            }
        }
    }
    fun getDepartmentsNames():MutableList<String>{
        var names = mutableListOf<String>()
        for(i in departments.value!!.department){
            names.add(i.name)
        }
        return names
    }

    fun getDepartmentIdByName(departmentName: String):Int{
        for(i in departments.value!!.department){
            if(i.name == departmentName){
                return i.ID
            }
        }
        return -1
    }


    fun getDepartmentNameById(id: Int):String{
        for(i in departments.value!!.department){
            if(i.ID == id){
                return i.name
            }
        }
        return ""
    }
}