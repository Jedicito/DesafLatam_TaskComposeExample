package com.example.taskcomposeexample.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskcomposeexample.data.Task
import com.example.taskcomposeexample.ui.theme.TaskComposeExampleTheme


@Composable
fun TaskList(
    task: List<Task>,
    onTaskClick: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = task,
            key = { it.id }
        ) { task ->
           TaskItem(
               task = task,
               onTaskClick = { onTaskClick(task) }
           )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun TaskListPreview() {
    val list = listOf(
        Task(1, "one", true),
        Task(2, "two", false)
    )

    TaskComposeExampleTheme {
        TaskList(
            task = list,
            onTaskClick = { }
        )
    }

}