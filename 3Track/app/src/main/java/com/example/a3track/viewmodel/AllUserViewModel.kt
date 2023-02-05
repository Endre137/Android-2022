package com.example.a3track.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.a3track.MyApplication
import com.example.a3track.model.GetCuResponse
import com.example.a3track.repository.TrackerRepository
import kotlinx.coroutines.launch


class AllUserViewModelFactory(
    private val repository: TrackerRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AllUserViewModel( repository) as T
    }
}
data class AllUsers(
    var users: MutableList<GetCuResponse> = mutableListOf()
)


class AllUserViewModel(val repository: TrackerRepository):ViewModel() {
    private val allUsers = MutableLiveData(AllUsers())

    fun getAllUsers(token: String) {
        viewModelScope.launch {
            try {
                val response = repository.getUsers(MyApplication.token)
                if (response.isSuccessful) {
                    val responses = response.body().toString().trim().split("),")
                    for (i in responses) {
                        val currentResponse = i.split(",")
                        val tId = currentResponse[0].split("=").get(1).toInt()
                        val tLastName = currentResponse[1].split("=").get(1)
                        val tFirstName = currentResponse[2].split("=").get(1)
                        val tEmail = currentResponse[3].split("=").get(1)
                        val tType = currentResponse[4].split("=").get(1).toInt()
                        val tLocation = currentResponse[5].split("=").get(1)
                        val tPhoneNumber = currentResponse[6].split("=").get(1)
                        val tDepId = currentResponse[7].split("=").get(1).toInt()
                        val tImageUrl = currentResponse[8].split("=").get(1).dropLast(1)

                        allUsers.value!!.users.add(
                            GetCuResponse(
                                tId,
                                tLastName,
                                tFirstName,
                                tEmail,
                                tType,
                                tLocation,
                                tPhoneNumber,
                                tDepId,
                                tImageUrl
                            )
                        )
                    }
                }

            } catch (ex: Exception) {
                Log.i("AVMError", ex.message.toString())

            }
        }
    }

            fun getName(id: Int):String?{
                for(i in allUsers.value!!.users){
                    if(i.ID == id){
                        return "${i.first_name} ${i.last_name}"
                    }
                }
                return null
            }


            fun getImage(id: Int):String?{
                for(i in allUsers.value!!.users){
                    if(i.ID == id){
                        return i.image
                    }
                }
                return null
            }

            fun getUserByDepType(depId : Int, type : Int):GetCuResponse?{
                for(i in allUsers.value!!.users){
                    if(i.department_id == depId && i.type == type){
                        return i
                    }
            }
            return null
        }
}

