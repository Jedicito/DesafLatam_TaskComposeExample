package com.example.taskcomposeexample.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskcomposeexample.domain.model.Task
import com.example.taskcomposeexample.domain.usecase.AddTaskUseCase
import com.example.taskcomposeexample.domain.usecase.GetTaskUseCase
import com.example.taskcomposeexample.presentation.uimodel.TaskFilter
import com.example.taskcomposeexample.presentation.uimodel.TaskUiEvent
import com.example.taskcomposeexample.presentation.uimodel.TaskUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getTaskUseCase: GetTaskUseCase,
    private val addTaskUseCase: AddTaskUseCase
) : ViewModel() {

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
            _uiState.update { state ->
                state.copy(tasks = filteredTask, filter = filter)
            }
        }.launchIn(viewModelScope)

        getTasks()
    }

    fun onEvent(event: TaskUiEvent) {
        when (event) {
            is TaskUiEvent.AddTask -> addTask(event.title, event.description)
            is TaskUiEvent.ToggleTask -> toggleTask(event.taskId)
            is TaskUiEvent.SetFilter -> setFilter(event.filter)
            is TaskUiEvent.DeleteTask -> deleteTask(event.taskId)
            else -> {} // Navigation events handled in UI
        }
    }

    private fun getTasks() = viewModelScope.launch {
        getTaskUseCase()
            .onStart {
                _uiState.update { it.copy(isLoading = true) }
            }
            .catch { error ->
                _uiState.update { it.copy(isError = error.message.toString()) }
            }
            .collect {  tasks ->
                _uiState.update { it.copy(
                    tasks = tasks,
                    isLoading = false
                ) }
            }
    }


    private fun deleteTask(taskId: Int) {
        _tasks.update { task ->
            task.filter { it.id != taskId }
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

    private fun addTask(title: String, description: String) = viewModelScope.launch {
        try {
            addTaskUseCase(title, description)
        } catch (e: Exception) {
            _uiState.update {
                it.copy(
                    isError = e.message.toString()
                )
            }
        }
    }

}