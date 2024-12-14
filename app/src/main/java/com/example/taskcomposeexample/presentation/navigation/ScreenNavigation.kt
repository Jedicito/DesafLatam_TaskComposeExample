package com.example.taskcomposeexample.presentation.navigation

sealed class ScreenNavigation(val route: String) {

    data object TaskList : ScreenNavigation("tasks")
    data object Detail : ScreenNavigation("detail/{id}") {
        fun createRoute(id: String) = "detail/$id"
    }
    data object AddTask : ScreenNavigation("addTask")
}