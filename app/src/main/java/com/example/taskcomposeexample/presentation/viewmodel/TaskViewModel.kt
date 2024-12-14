package com.example.taskcomposeexample.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskcomposeexample.data.model.Task
import com.example.taskcomposeexample.presentation.uimodel.TaskFilter
import com.example.taskcomposeexample.presentation.uimodel.TaskUiEvent
import com.example.taskcomposeexample.presentation.uimodel.TaskUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update

class TaskViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: MutableStateFlow<TaskUiState> = _uiState

    private val _tasks = MutableStateFlow<List<Task>>(emptyList())
    private val _filter = MutableStateFlow(TaskFilter.All)

    init {
        combine(_tasks, _filter) { task, filter ->
            val filteredTask = when (filter) {
                TaskFilter.All -> task
                TaskFilter.COMPLETED -> task.filter { it.isCompleted }
                TaskFilter.UNCOMPLETED -> task.filter { !it.isCompleted }
            }
            _uiState.value.copy(
                tasks = filteredTask,
                filter = filter
            )
        }.launchIn(viewModelScope)
    }

    fun onEvent(event: TaskUiEvent) {
        when (event) {
            is TaskUiEvent.AddTask -> addTask(event.title, event.description)
            is TaskUiEvent.ToggleTask -> toggleTask(event.taskId)
            is TaskUiEvent.SetFilter -> setFilter(event.filter)
            else -> {} // Navigation events handled in UI
        }
    }

    private fun setFilter(filter: TaskFilter) {
        _filter.value = filter
    }

    private fun toggleTask(taskId: Int) {
        _tasks.update { tasks ->
            tasks.map { task ->
                if (task.id == taskId) {
                    task.copy(isCompleted = !task.isCompleted)
                } else {
                    task
                }
            }
        }
    }

    private fun addTask(title: String, description: String) {
        if (title.isNotBlank()) {
            val newTask = Task(
                id = _tasks.value.size + 1,
                title = title,
                description = description
            )
            _tasks.update { it + newTask }
        }
    }

}