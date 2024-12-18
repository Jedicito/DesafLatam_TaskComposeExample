package com.example.taskcomposeexample.presentation.ui.list.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskcomposeexample.ui.theme.TaskComposeExampleTheme

@Composable
fun TaskInput(
    value: String,
    onValueChange: (String) -> Unit,
    onAddTask: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text("Nueva Tarea") },
            modifier = Modifier.weight(1f)
        )

        Button(
            onClick = onAddTask,
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text("Añadir")
        }

    }

}


@Preview(showBackground = true)
@Composable
fun TaskInputPreview() {
    TaskComposeExampleTheme {
        TaskInput(
            value = "",
            onValueChange = {},
            onAddTask = {}
        )
    }
}