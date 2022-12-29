package com.example.a3track.model

data class Task(
    var ID: Long,
    var title: String,
    var description: String,
    var created_time: Long,
    var created_by_user_ID: Long,
    var asigned_to_user_ID: Long,
    var priority: Int,
    var deadline: Long,
    var department_ID: Int,
    var status: Int
)
