package com.example.taskcomposeexample.data.mapper

import com.example.taskcomposeexample.data.local.TaskEntity
import com.example.taskcomposeexample.domain.model.Task

class TaskMapper {

     fun toDomain(entity: TaskEntity) : Task {
        return Task(
            id = entity.id,
            title = entity.title,
            description = entity.description,
            isCompleted = entity.isCompleted,
            createdAt = entity.createdAt
        )
    }

     fun toEntity(domain: Task): TaskEntity {
        return TaskEntity(
            id = domain.id,
            title = domain.title,
            description = domain.description,
            isCompleted = domain.isCompleted,
            createdAt = domain.createdAt
        )
    }

    fun toDomainList(entities: List<TaskEntity>): List<Task> {
        return entities.map { toDomain(it) }
    }

    fun toEntityList(domains: List<Task>): List<TaskEntity> {
        return domains.map { toEntity(it) }
    }

}