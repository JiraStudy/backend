package com.jirastudy.backend

import org.springframework.stereotype.Component

@Component
class TaskRepository{

    private val tasks = mutableMapOf<Long, Task>()

    fun getAll() = tasks.values.sortedWith(
            compareBy({ it.deadline }, { it.difficulty })
    )

    fun getById(id: Long): Task? = tasks[id]

    fun save(taskRequest: TaskRequest) = save(tasks.size.toLong(), taskRequest)

    fun update(id: Long, taskRequest: UpdateTaskRequest): Task? {
        return tasks[id]?.let {
            val task = Task(
                    id,
                    taskRequest.title ?: it.title,
                    taskRequest.deadline ?: it.deadline,
                    taskRequest.difficulty ?: it.difficulty,
                    taskRequest.type ?: it.type,
                    taskRequest.status ?: it.status,
            )
            tasks[id] = task
            task
        }
    }

    private fun save(id: Long, taskRequest: TaskRequest): Task {
        val task = Task(
                id,
                taskRequest.title,
                taskRequest.deadline,
                taskRequest.difficulty,
                taskRequest.type,
                taskRequest.status,
        )
        tasks[task.id] = task
        return task
    }

    fun delete(id: Long) = tasks.remove(id)
}
