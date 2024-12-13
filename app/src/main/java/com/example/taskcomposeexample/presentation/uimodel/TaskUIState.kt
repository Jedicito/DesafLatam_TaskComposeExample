package com.example.taskcomposeexample.presentation.uimodel

import com.example.taskcomposeexample.data.Task

sealed class TaskUIState {
    data class Success(
        val listTask: List<Task> = emptyList(),
    ) : TaskUIState()

    data object IsLoading : TaskUIState()

    data class Error(
        val message: String
    ) : TaskUIState()
}
