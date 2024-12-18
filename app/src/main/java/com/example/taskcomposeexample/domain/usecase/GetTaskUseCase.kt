package com.example.taskcomposeexample.domain.usecase

import com.example.taskcomposeexample.domain.model.Task
import com.example.taskcomposeexample.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTaskUseCase  @Inject constructor(
    private val repository: TaskRepository
) {
    operator fun invoke(): Flow<List<Task>> = repository.getTasks()
}