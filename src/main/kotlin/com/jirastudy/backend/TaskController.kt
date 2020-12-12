package com.jirastudy.backend

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TaskController(
    @Autowired val repository: TaskRepository,
) {

    @GetMapping("/tasks")
    fun getTasks(): List<Task> {
        return repository.findAll().toList()
    }
}