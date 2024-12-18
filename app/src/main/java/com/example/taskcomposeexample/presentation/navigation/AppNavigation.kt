package com.example.taskcomposeexample.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.taskcomposeexample.presentation.ui.detail.DetailTaskScreen
import com.example.taskcomposeexample.presentation.ui.list.FullScreenDialog
import com.example.taskcomposeexample.presentation.ui.list.TaskListScreen
import com.example.taskcomposeexample.presentation.uimodel.TaskUiEvent
import com.example.taskcomposeexample.presentation.viewmodel.TaskViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = ScreenNavigation.TaskList.route
    ) {
        // ListScreen
        composable(ScreenNavigation.TaskList.route) {
            val viewModel = hiltViewModel<TaskViewModel>()
            TaskListScreen(
                viewModel = viewModel,
                onNavigate = { event ->
                    when(event) {
                        is TaskUiEvent.NavigateToAdd -> {
                            navController.navigate(ScreenNavigation.AddTask.route)
                        }
                        is TaskUiEvent.NavigateToDetail -> {
                            navController.navigate(ScreenNavigation.Detail.createRoute(event.taskId))
                        }
                        else -> {
                          //  viewModel.onEvent(event)
                        }
                    }
                }
            )
        }

        composable(
            route = ScreenNavigation.Detail.route,
            arguments = listOf(navArgument("id") { type = NavType.IntType} )
        ) { backStackEntry ->
            val viewModel = hiltViewModel<TaskViewModel>()
            val navArgument = backStackEntry.arguments?.getInt("id") ?: 0
            DetailTaskScreen(
                taskId = navArgument,
                viewModel =  viewModel,
                onBack = { navController.popBackStack() },
                onToggleCompletion = { taskId ->
                    viewModel.onEvent(TaskUiEvent.ToggleTask(taskId = taskId))
                },
                onRemoveTask = { taskId ->
                    viewModel.onEvent(TaskUiEvent.DeleteTask(taskId))
                    navController.popBackStack()
                }
            )
        }

        composable(
            route = ScreenNavigation.AddTask.route
        ) { backStackEntry ->
            val viewModel = hiltViewModel<TaskViewModel>()
            FullScreenDialog(
                showDialog = true,
                onClose = { navController.popBackStack() },
                onSave = { title, description ->
                    viewModel.onEvent(TaskUiEvent.AddTask(title, description))
                    navController.popBackStack()
                }
            )
        }
    }
}

