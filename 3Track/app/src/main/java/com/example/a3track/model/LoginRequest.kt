package com.example.a3track.model
import com.squareup.moshi.JsonClass
class LoginRequest {
    @JsonClass(generateAdapter = true)
    data class LoginRequest(var email: String, var passwordKey: String )

}