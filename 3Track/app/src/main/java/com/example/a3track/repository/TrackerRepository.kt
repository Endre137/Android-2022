package com.example.a3track.repository

import com.example.a3track.api.RetrofitInstance
import com.example.a3track.model.LoginRequest
import com.example.a3track.model.LoginResponse
import com.example.a3track.model.Task
import com.example.a3track.model.User
import retrofit2.Response


class TrackerRepository {
    suspend fun login(request: LoginRequest.LoginRequest): Response<LoginResponse.LoginResponse> {
        return RetrofitInstance.api.login(request)
    }

    suspend fun getUsers(token: String): Response<List<User>> {
        return RetrofitInstance.api.getUsers(token)
    }

    suspend fun getTasks(token: String) : Response<List<Task>> {
        return RetrofitInstance.api.getTasks(token)
    }
}