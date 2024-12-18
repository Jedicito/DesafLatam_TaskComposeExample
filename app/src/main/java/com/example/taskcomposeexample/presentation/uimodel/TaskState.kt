package com.example.taskcomposeexample.presentation.uimodel

data class TaskUiState(
    val tasks: List<TaskUiModel> = emptyList(),
    val filteredTask: List<TaskUiModel> = emptyList(),
    val currentFilter: TaskFilter = TaskFilter.All,
    val isLoading: Boolean = false,
    val isError: String = ""
)

enum class TaskFilter {
    All,
    COMPLETED,
    UNCOMPLETED
}
