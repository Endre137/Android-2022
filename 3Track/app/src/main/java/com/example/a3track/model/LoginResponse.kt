package com.example.a3track.model
import com.squareup.moshi.JsonClass


class LoginResponse {
    @JsonClass(generateAdapter = true)
    data class LoginResponse (
        var userId: Int,
        var token: String,
        var deadline: Long
    )
}