package com.jirastudy.backend

import org.springframework.stereotype.Component

@Component
class SubTaskRepository{

    private val subTasks = mutableMapOf<Long, MutableMap<Long, SubTask>>()

    fun getAllByParentId(id: Long) = subTasks[id]?.values?.sortedWith(
            compareBy { it.difficulty }
    )

    fun getById(parentId: Long, id: Long): SubTask? = subTasks[parentId]?.get(id)

    fun save(parentId: Long, taskRequest: SubTaskRequest): SubTask {
        val parentSubTasks = subTasks.getOrPut(parentId) { mutableMapOf() }
        val subTask = SubTask(
                (parentSubTasks.keys.toList().max() ?: 0L) + 1L,
                taskRequest.title,
                taskRequest.difficulty,
                taskRequest.type,
                taskRequest.status ?: TaskStatus.TODO,
        )
        parentSubTasks[subTask.id] = subTask
        return subTask
    }

    fun update(parentId: Long, id: Long, taskRequest: UpdateSubTaskRequest): SubTask? {
        return subTasks[parentId]?.get(id)?.let {
            val subTask = SubTask(
                    id,
                    taskRequest.title ?: it.title,
                    taskRequest.difficulty ?: it.difficulty,
                    taskRequest.type ?: it.type,
                    taskRequest.status ?: it.status,
            )
            subTasks[parentId]?.put(id, subTask)
            subTask
        }
    }

    fun delete(parentId: Long, id: Long): SubTask? {
        return subTasks[parentId]?.remove(id)
    }
}
