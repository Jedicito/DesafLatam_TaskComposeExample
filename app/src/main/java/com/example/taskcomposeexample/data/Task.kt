package com.example.taskcomposeexample.data

data class Task(
    val id: Int,
    val title: String,
    val completed: Boolean = false
)
