package com.example.taskcomposeexample.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.taskcomposeexample.data.model.Task
import com.example.taskcomposeexample.presentation.uimodel.TaskUiEvent
import com.example.taskcomposeexample.presentation.viewmodel.TaskViewModel
import com.example.taskcomposeexample.ui.theme.TaskComposeExampleTheme


@Composable
fun TaskListScreen(
    viewModel: TaskViewModel,
    onNavigate: (TaskUiEvent) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        modifier = Modifier,
    ) { innerPadding ->
        TaskList(
            task = uiState.tasks,
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


/*
TaskInput(
value = taskTitle.toString(),
onValueChange = { viewModel.updateText(it) },
onAddTask = {
    onNavigateToDetail(taskTitle.toString())
    // viewModel.addTask(taskTitle.toString())
    //    newTaskTitle = ""
}
)
*/


@Preview(showBackground = true)
@Composable
fun TaskAppPreview() {
    TaskComposeExampleTheme {
        // TaskApp()
    }
}