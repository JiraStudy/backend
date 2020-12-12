package com.jirastudy.backend

import org.springframework.stereotype.Component

@Component
class TaskRepository{

    private val tasks = mutableMapOf<Long, Task>()

    fun getAll() = tasks.values.sortedWith(
            compareBy({ it.deadline }, { it.difficulty })
    )

    fun getById(id: Long): Task? = tasks[id]

    fun save(taskRequest: TaskRequest): Task {
        val task = Task(
                (tasks.keys.toList().max() ?: 0L) + 1L,
                taskRequest.title,
                taskRequest.deadline,
                taskRequest.difficulty,
                taskRequest.type,
                taskRequest.status ?: TaskStatus.TODO,
        )
        tasks[task.id] = task
        return task
    }

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

    fun delete(id: Long) = tasks.remove(id)
}
