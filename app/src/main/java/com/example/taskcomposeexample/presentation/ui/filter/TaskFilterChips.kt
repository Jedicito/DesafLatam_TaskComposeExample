package com.example.taskcomposeexample.presentation.ui.filter

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.taskcomposeexample.presentation.uimodel.TaskFilter
import com.example.taskcomposeexample.ui.theme.TaskComposeExampleTheme

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
        // filter ALL
        FilterChip(
            selected = currentFilter == TaskFilter.All,
            onClick = { onFilterChange(TaskFilter.All) } ,
            label = { Text("ALL")},
            colors = FilterChipDefaults.filterChipColors(
                selectedLabelColor = MaterialTheme.colorScheme.primary,
                selectedContainerColor = MaterialTheme.colorScheme.onPrimary
            )
        )
        // filter completed
        FilterChip(
            selected = currentFilter == TaskFilter.COMPLETED,
            onClick = { onFilterChange(TaskFilter.COMPLETED) } ,
            label = { Text("Completed")},
            colors = FilterChipDefaults.filterChipColors(
                selectedLabelColor = MaterialTheme.colorScheme.primary,
                selectedContainerColor = MaterialTheme.colorScheme.onPrimary
            )
        )
        // uncompleted
        FilterChip(
            selected = currentFilter == TaskFilter.UNCOMPLETED,
            onClick = { onFilterChange(TaskFilter.UNCOMPLETED) } ,
            label = { Text("Uncompleted")},
            colors = FilterChipDefaults.filterChipColors(
                selectedLabelColor = MaterialTheme.colorScheme.primary,
                selectedContainerColor = MaterialTheme.colorScheme.onPrimary
            )
        )
    }
}

@Preview
@Composable
private fun ChipFilterPreview() {
    TaskComposeExampleTheme {
        ChipFilter(
            TaskFilter.COMPLETED
        ) { }
    }
}