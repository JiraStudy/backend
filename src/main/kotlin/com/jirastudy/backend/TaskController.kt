package com.jirastudy.backend

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@CrossOrigin
@RestController
class TaskController(
        @Autowired val repository: TaskRepository,
        @Autowired val subTaskRepository: SubTaskRepository,
) {

    @GetMapping("/tasks")
    fun getTask(@RequestParam completed: Boolean?): TaskListResponse {
        val tasks = repository.getAll()
        return TaskListResponse(
                (completed?.let { isCompleted ->
                    tasks.filter {
                        if (isCompleted) {
                            it.status == TaskStatus.DONE
                        } else {
                            it.status != TaskStatus.DONE
                        }
                    }
                } ?: tasks).map {
                    val subTasksCount = subTaskRepository.getAllByParentId(it.id)?.size ?: 0
                    it.toTaskResponse(subTasksCount)
                }
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
