package com.jirastudy.backend

data class TaskResponse(
        val data: List<Task>
)

data class TaskRequest(
        val title: String,
        val deadline: String,
        val difficulty: Int,
        val type: TaskType,
)

data class Task(
        val id: Long,
        val title: String,
        val deadline: String,
        val difficulty: Int,
        val type: TaskType,
)

enum class TaskType {
    TEST,
    ASSIGNMENT,
}