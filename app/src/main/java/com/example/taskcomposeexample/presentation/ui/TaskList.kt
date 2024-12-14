package com.example.taskcomposeexample.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskcomposeexample.data.model.Task
import com.example.taskcomposeexample.ui.theme.TaskComposeExampleTheme

@Composable
fun TaskList(
    task: List<Task>,
    onTaskClick: (Int) -> Unit,
    onTaskToggle: (Int) -> Unit,
    paddingValues: PaddingValues
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth().padding(paddingValues),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = task,
            key = { it.id }
        ) { task ->
           TaskItem(
               task = task,
               onTaskClick = { onTaskClick(task.id) },
               onTaskToggle = {onTaskToggle(task.id)}
           )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun TaskListPreview() {
    val list = listOf(
        Task(1, "one", "This is a description",true),
        Task(2, "two", "This is a description",false)
    )

    TaskComposeExampleTheme {
        TaskList(list, {}, {}, paddingValues = PaddingValues())
    }

}