package com.example.taskcomposeexample.presentation.ui.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.taskcomposeexample.presentation.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    taskId: Int,
    viewModel: TaskViewModel, // Pass the ViewModel instance
    onBack: () -> Unit,
    onToggleCompletion: (Int) -> Unit, // Toggle task completion
    onRemoveTask: (Int) -> Unit // Remove task by ID
) {

    val uiState by viewModel.uiState.collectAsState()
    // Fetch the task from the ViewModel using the taskId
    val task = uiState.tasks.find { it.id == taskId }

    // Handle case when the task is not found
    if (task == null) {
        Text(
            text = "Task not found",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            textAlign = TextAlign.Center
        )
        return
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Task Details") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Task Title
            Text(
                text = task.title,
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Task Description
            Text(
                text = task.description,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Task Completion Status
            Text(
                text = if (task.isCompleted) "Status: Completed" else "Status: Not Completed",
                style = MaterialTheme.typography.bodySmall,
                color = if (task.isCompleted) Color.Green else Color.Red,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Start
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Toggle Completion Button
                Button(
                    onClick = { onToggleCompletion(task.id) },
                    shape = MaterialTheme.shapes.medium,
                    contentPadding = PaddingValues(12.dp)
                ) {
                    Text(text = if (task.isCompleted) "Mark as Incomplete" else "Mark as Complete")
                }

                // Remove Task Button
                Button(
                    onClick = {
                        onRemoveTask(task.id)
                        onBack() // Navigate back after removal
                    },
                    shape = MaterialTheme.shapes.medium,
                    contentPadding = PaddingValues(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red
                    )
                ) {
                    Text(text = "Remove Task", color = Color.White)
                }
            }
        }
    }
}