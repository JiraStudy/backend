package com.jirastudy.backend

data class SubTaskListResponse(
        val data: List<SubTask>
)

data class SubTaskRequest(
        val title: String,
        val difficulty: Int,
        val type: TaskType,
        val status: TaskStatus
)

data class UpdateSubTaskRequest(
        val title: String?,
        val difficulty: Int?,
        val type: TaskType?,
        val status: TaskStatus?,
)
