package com.example.taskcomposeexample.presentation.ui.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskcomposeexample.presentation.theme.TaskComposeExampleTheme
import com.example.taskcomposeexample.presentation.ui.filter.ChipFilter
import com.example.taskcomposeexample.presentation.ui.list.composables.TaskList
import com.example.taskcomposeexample.presentation.uimodel.TaskUiEvent
import com.example.taskcomposeexample.presentation.viewmodel.TaskViewModel

@Composable
fun TaskListScreen(
    viewModel: TaskViewModel,
    onNavigate: (TaskUiEvent) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onNavigate(TaskUiEvent.NavigateToAdd)
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
        ) {
            ChipFilter(
                currentFilter = uiState.currentFilter,
                onFilterChange = { filter ->
                    viewModel.onEvent(TaskUiEvent.SetFilter(filter))
                }
            )

            TaskList(
                task = uiState.filteredTask,
                onTaskClick = { taskId ->
                    onNavigate(TaskUiEvent.NavigateToDetail(taskId))
                },
                onTaskToggle = { taskId ->
                    onNavigate(TaskUiEvent.ToggleTask(taskId))
                },
                paddingValues = innerPadding
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun TaskAppPreview() {
    TaskComposeExampleTheme {
        // TaskApp()
    }
}