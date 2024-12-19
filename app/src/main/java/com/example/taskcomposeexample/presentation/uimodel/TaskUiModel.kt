package com.example.taskcomposeexample.presentation.uimodel

data class TaskUiModel(
    val id: Int,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val formattedDate: String
)