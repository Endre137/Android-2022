package com.example.a3track.repository
import retrofit2.Response
import retrofit2.Retrofit

class TrackerRepository {
    suspend fun login(request: LoginRequest) : Response<LoginResponse>{
        return RetrofitInstace.api.login(request)
    }
}