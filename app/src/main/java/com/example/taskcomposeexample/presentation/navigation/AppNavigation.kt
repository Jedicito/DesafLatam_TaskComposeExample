package com.example.taskcomposeexample.presentation.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskcomposeexample.presentation.ui.TaskApp
import com.example.taskcomposeexample.presentation.viewmodel.TaskViewModel

@Composable
fun AppNavigation(viewModel: TaskViewModel) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            TaskApp(viewModel,
                onNavigateToDetail = {
                    //navController.navigate("listDetail", "criss")
                }
            )
        }

        composable("listDetail/{id}") {
                Text("Hola ")
        }


    }
}


