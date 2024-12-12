package com.example.taskcomposeexample.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taskcomposeexample.data.Task

class TaskViewModel: ViewModel() {

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks

    private val _taskText = MutableLiveData("")
    val taskText: LiveData<String> = _taskText

    fun addTask(title: String) {
        if (title.isNotBlank()) {
            val taskSize = tasks.value?.size ?: 0
            val task = Task(
                    id = taskSize + 1,
                    title = title
                )
            _tasks.value = (_tasks.value ?: emptyList()) + task
        }
    }

    fun completeTask(task: Task) {
        _tasks.value?.map { item ->
            if (item.id == task.id) {
                item.copy(completed = !item.completed)
            } else item
        }
    }

}