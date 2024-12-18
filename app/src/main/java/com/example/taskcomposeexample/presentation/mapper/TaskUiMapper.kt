package com.example.taskcomposeexample.presentation.mapper

import com.example.taskcomposeexample.domain.model.Task
import com.example.taskcomposeexample.presentation.uimodel.TaskUiModel
import java.text.DateFormat
import java.util.Date
import javax.inject.Inject

class TaskUiMapper @Inject constructor() {

    private fun toUiModel(domain: Task): TaskUiModel {
        return TaskUiModel(
            id = domain.id,
            title = domain.title,
            description = domain.description,
            isCompleted = domain.isCompleted,
            formattedDate = formatDate(domain.createdAt)
        )
    }

    fun toDomain(uiModel: TaskUiModel): Task {
        return Task(
            id = uiModel.id,
            title = uiModel.title,
            description = uiModel.description,
            isCompleted = uiModel.isCompleted,
            createdAt = parseDate(uiModel.formattedDate)
        )
    }

    fun toTaskUiModelList(domainTask: List<Task>): List<TaskUiModel> {
        return domainTask.map { toUiModel(it) }
    }

    private fun formatDate(timestamp: Long): String {
        return DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT)
            .format(Date(timestamp))
    }

    private fun parseDate(formattedDate: String): Long {
        return try {
            DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT)
                .parse(formattedDate)?.time ?: System.currentTimeMillis()
        } catch (e: Exception) {
            System.currentTimeMillis()
        }
    }
}