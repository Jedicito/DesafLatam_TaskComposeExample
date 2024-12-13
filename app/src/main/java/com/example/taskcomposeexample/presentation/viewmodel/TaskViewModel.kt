package com.example.taskcomposeexample.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskcomposeexample.data.Task
import com.example.taskcomposeexample.presentation.uimodel.TaskUIState

class TaskViewModel: ViewModel() {

    private val _state = MutableLiveData<TaskUIState>(TaskUIState.Success())
    val state: LiveData<TaskUIState> = _state

    private val _taskText = MutableLiveData("")
    val taskText: LiveData<String> = _taskText

    fun updateText(text: String) {
        _taskText.value = text
    }

    fun addTask(title: String) {
        val successState = state.value as TaskUIState.Success
        if (title.isNotBlank()) {
            val taskSize = successState.listTask.size
            val task = Task(
                    id = taskSize + 1,
                    title = title
                )
            _state.value = successState.copy(
                listTask = (successState.listTask) + task
            )
            _taskText.value = ""
        }
    }

    fun completeTask(task: Task) {
        val successState = state.value as TaskUIState.Success
        val taskList = successState.listTask.map { item ->
            if (item.id == task.id) {
                item.copy(completed = !item.completed)
            } else item
        }

        _state.value = successState.copy(
            listTask = taskList
        )
    }
}