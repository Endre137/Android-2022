package com.example.a3track.model


data class User (
    var ID: Long,
    var lastName: String,
    var firstName: String,
    var email: String,
    var type: Int,
    var departmentId : Int
    )