package com.example.taskcomposeexample.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskcomposeexample.data.Task
import com.example.taskcomposeexample.presentation.uimodel.TaskUIState
import com.example.taskcomposeexample.presentation.viewmodel.TaskViewModel
import com.example.taskcomposeexample.ui.theme.TaskComposeExampleTheme


@Composable
fun TaskApp(
    viewModel: TaskViewModel,
    onNavigateToDetail: () -> Unit
) {
    val state by viewModel.state.observeAsState()
    val taskTitle by viewModel.taskText.observeAsState()

    //var newTaskTitle by remember { mutableStateOf("") }

    when(state) {
        is TaskUIState.Error -> { }
        TaskUIState.IsLoading -> { CircularProgressIndicator() }
        is TaskUIState.Success -> {
            TaskListComposable(
                taskTitle,
                viewModel,
                (state as TaskUIState.Success).listTask,
                onNavigateToDetail
                )
        }
        null -> {
        }
    }


}

@Composable
private fun TaskListComposable(
    taskTitle: String?,
    viewModel: TaskViewModel,
    listTask: List<Task>,
    onNavigateToDetail: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        // Input para nueva tarea
        TaskInput(
            value = taskTitle.toString(),
            onValueChange = { viewModel.updateText(it) },
            onAddTask = {
                onNavigateToDetail()
               // viewModel.addTask(taskTitle.toString())
                //    newTaskTitle = ""
            }
        )
        // Lista de tareas
        TaskList(
            task = listTask,
            onTaskClick = { viewModel.completeTask(task = it) }
        )

    }
}

@Preview(showBackground = true)
@Composable
fun TaskAppPreview() {
    TaskComposeExampleTheme {
        // TaskApp()
    }
}