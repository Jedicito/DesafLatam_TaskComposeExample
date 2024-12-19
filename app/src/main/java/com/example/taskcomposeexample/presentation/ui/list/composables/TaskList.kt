package com.example.taskcomposeexample.presentation.ui.list.composables

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
import com.example.taskcomposeexample.presentation.theme.TaskComposeExampleTheme
import com.example.taskcomposeexample.presentation.uimodel.TaskUiModel

@Composable
fun TaskList(
    task: List<TaskUiModel>,
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
        TaskUiModel(1, "one", "This is a description",true, ""),
        TaskUiModel(2, "two", "This is a description",false, "")
    )

    TaskComposeExampleTheme {
        TaskList(list, {}, {}, paddingValues = PaddingValues())
    }

}