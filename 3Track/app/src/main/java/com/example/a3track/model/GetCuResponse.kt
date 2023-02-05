package com.example.a3track.model

data class GetCuResponse (
    val ID: Int,
    val last_name: String,
    val first_name: String,
    val email: String,
    val type: Int,
    val location: String,
    val phone_number: String?,
    val department_id: Int,
    val image: String?
)