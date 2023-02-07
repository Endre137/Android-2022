package com.example.a3track.model

data class UpdateTaskRequest(
    val taskId:Int,
    val title: String,
    val description: String,
    val assignedToUserId: Int,
    val priority: Int,
    val deadline: Long,
    val departmentId: Int,
    val status:Int,
    val progress:Int
)
