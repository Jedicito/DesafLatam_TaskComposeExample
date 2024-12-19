package com.example.taskcomposeexample.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskcomposeexample.domain.usecase.AddTaskUseCase
import com.example.taskcomposeexample.domain.usecase.DeleteTaskUseCase
import com.example.taskcomposeexample.domain.usecase.GetTaskUseCase
import com.example.taskcomposeexample.domain.usecase.UpdateTaskUseCase
import com.example.taskcomposeexample.presentation.mapper.TaskUiMapper
import com.example.taskcomposeexample.presentation.uimodel.TaskFilter
import com.example.taskcomposeexample.presentation.uimodel.TaskUiEvent
import com.example.taskcomposeexample.presentation.uimodel.TaskUiModel
import com.example.taskcomposeexample.presentation.uimodel.TaskUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TaskViewModel @Inject constructor(
    private val getTaskUseCase: GetTaskUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val taskUiMapper: TaskUiMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow(TaskUiState())
    val uiState: MutableStateFlow<TaskUiState> = _uiState

    init {
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
            .map { domainTask ->
                taskUiMapper.toTaskUiModelList(domainTask)
            }
            .collect {  uiTasks ->
                _uiState.update { currentState ->
                    currentState.copy(
                    tasks = uiTasks,
                    filteredTask = filterTask(uiTasks, currentState.currentFilter),
                    isLoading = false
                ) }
            }
    }

    private fun filterTask(tasks: List<TaskUiModel>, filter: TaskFilter) : List<TaskUiModel> =
        when (filter) {
            TaskFilter.All -> tasks
            TaskFilter.COMPLETED -> tasks.filter { it.isCompleted }
            TaskFilter.UNCOMPLETED -> tasks.filter { !it.isCompleted }
        }

    private fun toggleTask(taskId: Int) = viewModelScope.launch {
        val taskToUpdate = uiState.value.tasks.find { it.id == taskId }
        taskToUpdate?.let {
            val task = it.copy(isCompleted = !taskToUpdate.isCompleted)
            updateTaskUseCase(taskUiMapper.toDomain(task))
        }
    }

    private fun deleteTask(taskId: Int) = viewModelScope.launch {
        val taskToDelete = uiState.value.tasks.find { it.id == taskId }
        taskToDelete?.let {
            deleteTaskUseCase(taskUiMapper.toDomain(it))
        }
    }

    private fun setFilter(filter: TaskFilter) {
        _uiState.update { currentState ->
            currentState.copy(
                currentFilter = filter,
                filteredTask = filterTask(currentState.tasks, filter)
            )
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