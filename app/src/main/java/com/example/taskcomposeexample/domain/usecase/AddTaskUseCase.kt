package com.example.taskcomposeexample.domain.usecase

import com.example.taskcomposeexample.domain.model.Task
import com.example.taskcomposeexample.domain.repository.TaskRepository
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(title: String, description: String) {
        val task = Task(
            title = title,
            description = description
        )
        repository.addTask(task)
    }
}
