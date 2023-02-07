package com.example.a3track.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.a3track.MyApplication
import com.example.a3track.model.GetCuResponse
import com.example.a3track.model.LoginResponse
import com.example.a3track.repository.TrackerRepository
import kotlinx.coroutines.launch

data class User(
    var ID: Int = 0,
    var department_id: Int = 0,
    var email: String = "",
    var first_name: String = "",
    var last_name: String = "",
    var location: String? = null,
    var phone_number: String? = null,
    var type: Int = 0,
    var loginResponse: LoginResponse.LoginResponse = LoginResponse.LoginResponse(0,"",420),
    var imageUrl : String = "",
    var mentor : GetCuResponse? = null
): java.io.Serializable

class UserViewModelFactory(
    private val repository: TrackerRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return UserViewModel( ) as T
    }
}
class UserViewModel():ViewModel(){
    private val userData = MutableLiveData(User())
    val repository = TrackerRepository()
    fun getDeadline():Long{
        return userData.value?.loginResponse?.deadline ?: 0
    }

    fun getImageUrl():String{
        return userData.value!!.imageUrl
    }

    fun getTelephoneNumber():String? {
        return userData.value!!.phone_number
    }

    fun getMentorId():Int{
        return  userData.value!!.mentor!!.ID
    }

    fun getEmail():String{
        return  userData.value!!.email
    }

    fun getToken():String{
        return userData.value?.loginResponse?.token ?: ""
    }
    fun getDepartmentId() : Int{
        return userData.value?.department_id ?: 0
    }

    fun getName(): String{
        Log.i("Username", "${userData.value?.first_name} ${userData.value?.last_name}")
        return "${userData.value?.first_name} ${userData.value?.last_name}"
    }
    fun getCurrentUser():Boolean{
        var responseCorrect = true

        viewModelScope.launch {
            try{
                val response = repository.getCurrentUser(MyApplication.token)
                if(response.isSuccessful){
                    val response = response.body().toString().trim().split(",")
                    val responses : MutableList<String> = mutableListOf()
                    for(i in response){
                        val t = i.split("=")[1]
                        responses.add(t)
                    }

                    for(i in responses){
                        Log.i("CurrentUser",i)
                    }
                    userData.value?.ID = responses[0].toInt()
                    userData.value?.first_name = responses[1]
                    userData.value?.last_name = responses[2]
                    userData.value?.email = responses[3]
                    userData.value?.type = responses[4].toInt()
                    userData.value?.location = responses[5]
                    userData.value?.phone_number = responses[6]
                    userData.value?.department_id = responses[7].toInt()
                    userData.value?.imageUrl = responses[8].dropLast(1)
                    userData.value?.loginResponse = LoginResponse.LoginResponse(getDeadline().toInt(),getToken(),responses[0].toLong())


                }


            }catch(ex :Exception){
                Log.i("UVMError", ex.message.toString())
                responseCorrect = false
            }
        }
        return responseCorrect
    }


}
