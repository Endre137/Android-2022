package com.example.a3track.repository

import com.example.a3track.MyApplication.Companion.token
import com.example.a3track.api.RetrofitInstance
import com.example.a3track.model.*
import retrofit2.Response


class TrackerRepository {
    suspend fun login(request: LoginRequest.LoginRequest): Response<LoginResponse.LoginResponse> {
        return RetrofitInstance.api.login(request)
    }

    suspend fun getUsers(token: String): Response<List<User>> {
        return RetrofitInstance.api.getUsers(token)
    }

    suspend fun getTasks(token: String) : Response<MutableList<Task>> {
        return RetrofitInstance.api.getTasks(token)
    }

    suspend fun getCurrentUser(userRequest: String) : Response<GetCuResponse> {
        return RetrofitInstance.api.getCurrentUser(token)
    }
}