package com.example.taskcomposeexample.presentation.uimodel

import com.example.taskcomposeexample.data.Task

data class TaskUIState(
    val listTask: List<Task> = emptyList(),
    val loading: Boolean = false,
    val error: String? = ""
)
