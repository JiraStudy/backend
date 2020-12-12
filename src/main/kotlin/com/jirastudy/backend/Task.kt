package com.jirastudy.backend

data class TaskListResponse(
        val data: List<TaskResponse>
)

data class TaskRequest(
        val title: String,
        val deadline: String,
        val difficulty: Int,
        val type: String,
        val status: TaskStatus?,
)

data class UpdateTaskRequest(
        val title: String?,
        val deadline: String?,
        val difficulty: Int?,
        val type: String?,
        val status: TaskStatus?,
)

data class Task(
        val id: Long,
        val title: String,
        val deadline: String,
        val difficulty: Int,
        val type: String,
        val status: TaskStatus,
)

data class TaskResponse(
        val id: Long,
        val title: String,
        val deadline: String,
        val difficulty: Int,
        val type: String,
        val status: TaskStatus,
        val subTasksCount: Int
)

enum class TaskStatus {
    TODO,
    IN_PROGRESS,
    DONE,
}

fun Task.toTaskResponse(subTasksCount: Int = 0): TaskResponse {
    return TaskResponse(
            this.id,
            this.title,
            this.deadline,
            this.difficulty,
            this.type,
            this.status,
            subTasksCount
    )
}
