package com.example.taskcomposeexample.presentation.uimodel

sealed class TaskUiEvent {
    data class AddTask(val title: String, val description: String) : TaskUiEvent()
    data class ToggleTask(val taskId: Int) : TaskUiEvent()
    data class SetFilter(val filter: TaskFilter) : TaskUiEvent()
    data object NavigateToAdd : TaskUiEvent()
    data class NavigateToDetail(val taskId: Int) : TaskUiEvent()
}