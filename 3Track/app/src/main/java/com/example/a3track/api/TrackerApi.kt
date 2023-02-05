package com.example.a3track.api


import com.example.a3track.model.*
import com.example.a3track.util.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TrackerApi {
    @POST(Constants.LOGIN_URL)
    suspend fun login(@Body request: LoginRequest.LoginRequest): Response<LoginResponse.LoginResponse>

    @GET(Constants.GET_USERS_URL)
    suspend fun getUsers(@Header("token") token: String): Response<List<GetCuResponse>>

    @GET(Constants.GET_TASKS_URL)
    suspend fun getTasks(@Header("token") token: String): Response<MutableList<Task>>

    @GET(Constants.GET_CURRENT_USER_URL)
    suspend fun getCurrentUser(@Header("token") token: String): Response<GetCuResponse>
}
