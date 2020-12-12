package com.jirastudy.backend

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class TaskController(
        @Autowired val repository: TaskRepository,
) {

    @GetMapping("/tasks")
    fun getTask(@RequestParam completed: Boolean?): TaskResponse {
        val tasks = repository.getAll()
        return TaskResponse(
                completed?.let { isCompleted ->
                    tasks.filter {
                        if (isCompleted) {
                            it.status == Status.DONE
                        } else {
                            it.status != Status.DONE
                        }
                    }
                } ?: tasks
        )
    }

    @GetMapping("/tasks/{id}")
    fun getTask(@PathVariable id: Long) = repository.getById(id) ?: throw ResourceNotFoundException()

    @PostMapping("/tasks")
    fun postTask(@RequestBody postBody: TaskRequest) = repository.save(postBody)

    @PutMapping("/tasks/{id}")
    fun updateTask(@PathVariable id: Long, @RequestBody putBody: UpdateTaskRequest) =
        repository.update(id, putBody) ?: throw ResourceNotFoundException()

    @DeleteMapping("/tasks/{id}")
    fun deleteTask(@PathVariable id: Long) = repository.delete(id)
}
