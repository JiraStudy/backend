package com.jirastudy.backend

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class SubTaskController(
        @Autowired val repository: SubTaskRepository,
) {

    @GetMapping("/tasks/{parentId}/subtasks")
    fun getTask(@PathVariable parentId: Long, @RequestParam completed: Boolean?): SubTaskListResponse {
        val tasks = repository.getAllByParentId(parentId) ?: listOf()
        return SubTaskListResponse(
                completed?.let { isCompleted ->
                    tasks.filter {
                        if (isCompleted) {
                            it.status == TaskStatus.DONE
                        } else {
                            it.status != TaskStatus.DONE
                        }
                    }
                } ?: tasks
        )
    }

    @GetMapping("/tasks/{parentId}/subtasks/{id}")
    fun getTask(@PathVariable parentId: Long, @PathVariable id: Long) =
            repository.getById(parentId, id) ?: throw ResourceNotFoundException()

    @PostMapping("/tasks/{parentId}/subtasks")
    fun postTask(@PathVariable parentId: Long, @RequestBody postBody: SubTaskRequest) =
            repository.save(parentId, postBody)

    @PutMapping("/tasks/{parentId}/subtasks/{id}")
    fun updateTask(@PathVariable parentId: Long, @PathVariable id: Long, @RequestBody putBody: UpdateSubTaskRequest) =
            repository.update(parentId, id, putBody) ?: throw ResourceNotFoundException()

    @DeleteMapping("/tasks/{parentId}/subtasks/{id}")
    fun deleteTask(@PathVariable parentId: Long, @PathVariable id: Long) = repository.delete(parentId, id)
}
