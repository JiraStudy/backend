package com.jirastudy.backend

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class TaskController(
        @Autowired val repository: TaskRepository,
) {

    @GetMapping("/tasks")
    fun getTask() = repository.getAll()

    @PostMapping("/tasks")
    fun postTask(@RequestBody postBody: TaskRequest) = repository.save(postBody)

    @PutMapping("/tasks/{id}")
    fun updateTask(@PathVariable id: Long, @RequestBody putBody: TaskRequest) = repository.update(id, putBody)

    @DeleteMapping("/tasks/{id}")
    fun deleteTask(@PathVariable id: Long) = repository.delete(id)
}