package com.example.taskcomposeexample.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskcomposeexample.presentation.ui.TaskListScreen
import com.example.taskcomposeexample.presentation.viewmodel.TaskViewModel

@Composable
fun AppNavigation(viewModel: TaskViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ScreenNavigation.TaskList.route
    ) {
        composable(ScreenNavigation.TaskList.route) {
            TaskListScreen(
                viewModel = viewModel,
                onNavigate = { event ->
                    viewModel.onEvent(event)
                }
            )
        }

        composable(
            route = ScreenNavigation.Detail.route,
        ) { backStackEntry ->
            val navArgument = backStackEntry.arguments?.getString("id") ?: ""
            // TODO implementar el detalle de la task
        }

        composable(
            route = ScreenNavigation.AddTask.route
        ) { backStackEntry ->
            // TODO implementar dialog para añadir tarea
        }
    }
}

