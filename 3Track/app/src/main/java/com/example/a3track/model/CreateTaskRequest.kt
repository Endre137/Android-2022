package com.example.a3track.model
import com.google.gson.annotations.SerializedName
data class CreateTaskRequest(
    val title: String,
    val description: String,
    val assignedToUserId: Int,
    val priority: Int,
    val deadline: Long,
    val departmentId: Int,
    val status:Int
    )
