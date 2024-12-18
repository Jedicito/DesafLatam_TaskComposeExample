package com.example.taskcomposeexample.presentation.uimodel

import com.example.taskcomposeexample.domain.model.Task

data class TaskUiState(
    val tasks: List<Task> = emptyList(),
    val filter: TaskFilter = TaskFilter.All,
    val isLoading: Boolean = false,
    val isError: String = ""
)

enum class TaskFilter {
    All,
    COMPLETED,
    UNCOMPLETED
}
