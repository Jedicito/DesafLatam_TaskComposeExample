package com.example.taskcomposeexample.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskcomposeexample.presentation.viewmodel.TaskViewModel
import com.example.taskcomposeexample.ui.theme.TaskComposeExampleTheme


@Composable
fun TaskApp(
    viewModel: TaskViewModel
) {

    val tasks by viewModel.tasks.observeAsState()
    val taskTitle by viewModel.taskText.observeAsState()

    var newTaskTitle by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        // Input para nueva tarea
        TaskInput(
            value = newTaskTitle,
            onValueChange = { newTaskTitle = it },
            onAddTask = {
                    viewModel.addTask(newTaskTitle)
                    //newTaskTitle = ""
            }
        )
        Log.d("CRISS", "task outside $tasks")
        // Lista de tareas
        tasks?.let {
            Log.d("CRISS", "Task List is not null $it")
            TaskList(
                task = it,
                onTaskClick = { viewModel.completeTask(task = it)}
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