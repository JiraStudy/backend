package com.jirastudy.backend

data class SubTaskListResponse(
        val data: List<SubTask>
)

data class SubTaskRequest(
        val title: String,
        val difficulty: Int,
        val type: String,
        val status: TaskStatus?
)

data class UpdateSubTaskRequest(
        val title: String?,
        val difficulty: Int?,
        val type: String?,
        val status: TaskStatus?,
)

data class SubTask(
        val id: Long,
        val title: String,
        val difficulty: Int,
        val type: String,
        val status: TaskStatus,
)
