package com.example.taskcomposeexample.presentation.navigation



sealed class Screen(val route: String) {
    data object ListScreen : Screen("home")
    data object Detail : Screen("detail/{id}") {
        fun createRoute(id: Int) = "detail/$id"
    }
}