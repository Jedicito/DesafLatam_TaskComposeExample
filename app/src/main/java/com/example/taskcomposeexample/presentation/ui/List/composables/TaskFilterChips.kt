package com.example.taskcomposeexample.presentation.ui.List.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.taskcomposeexample.presentation.uimodel.TaskFilter

@Composable
fun ChipFilter(
    currentFilter: TaskFilter,
    onFilterChange: (TaskFilter) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        // All Tasks Chip
        FilterChip(
            selected = currentFilter == TaskFilter.All,
            onClick = { onFilterChange(TaskFilter.All) },
            label = { Text("All") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = MaterialTheme.colorScheme.primary,
                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
            )
        )

        // Completed Tasks Chip
        FilterChip(
            selected = currentFilter == TaskFilter.COMPLETED,
            onClick = { onFilterChange(TaskFilter.COMPLETED) },
            label = { Text("Completed") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor =  MaterialTheme.colorScheme.primary,
                selectedLabelColor =  MaterialTheme.colorScheme.onPrimary
            )
        )

        // Uncompleted Tasks Chip
        FilterChip(
            selected = currentFilter == TaskFilter.UNCOMPLETED,
            onClick = { onFilterChange(TaskFilter.UNCOMPLETED) },
            label = { Text("Uncompleted") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = MaterialTheme.colorScheme.primary,
                selectedLabelColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }
}