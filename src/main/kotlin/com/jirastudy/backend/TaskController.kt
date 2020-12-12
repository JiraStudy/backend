package com.jirastudy.backend

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TaskController {

    @GetMapping("/tasks")
    fun getTasks() = List(3) {
        Task(23L, "sdfsdfl-$it", "sdfsdf")
    }
}