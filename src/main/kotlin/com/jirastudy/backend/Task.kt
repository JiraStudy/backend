package com.jirastudy.backend

data class TaskResponse(
        val data: List<Task>
)

data class TaskRequest(
        val title: String,
        val deadline: String,
        val difficulty: Int,
        val type: TaskType,
        val status: Status,
)

data class UpdateTaskRequest(
        val title: String?,
        val deadline: String?,
        val difficulty: Int?,
        val type: TaskType?,
        val status: Status?,
)

data class Task(
        val id: Long,
        val title: String,
        val deadline: String,
        val difficulty: Int,
        val type: TaskType,
        val status: Status,
)

enum class Status {
    TODO,
    IN_PROGRESS,
    DONE,
}

enum class TaskType {
    TEST,
    ASSIGNMENT,
}
