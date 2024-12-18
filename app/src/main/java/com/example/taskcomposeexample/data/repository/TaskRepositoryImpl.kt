package com.example.taskcomposeexample.data.repository

import android.util.Log
import com.example.taskcomposeexample.data.local.TaskDao
import com.example.taskcomposeexample.data.mapper.TaskMapper
import com.example.taskcomposeexample.domain.model.Task
import com.example.taskcomposeexample.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(
    private val taskDao: TaskDao,
    private val mapper: TaskMapper
): TaskRepository {

    override fun getTasks(): Flow<List<Task>> {
      return taskDao.getAllTask().map { entities ->
           mapper.toDomainList(entities)
       }.catch { exception ->
           Log.e("TaskRepositoryImpl", exception.message.toString())
           emit(emptyList())
      }
    }

    override suspend fun addTask(task: Task) {
        try {
            taskDao.insertTask(mapper.toEntity(task))
        } catch (e: Exception) {
            Log.e("TaskRepositoryImpl", e.message.toString())
            throw TaskException.InsertionError(e.message.toString(), e)
        }
    }

    override suspend fun updateTask(task: Task) {
        try {
            taskDao.updateTask(mapper.toEntity(task))
        } catch (e: Exception) {
            Log.e("TaskRepositoryImpl", e.message.toString())
        }
    }

    override suspend fun deleteTask(task: Task) {
        try {
            taskDao.deleteTask(mapper.toEntity(task))
        } catch (e: Exception) {
            Log.e("TaskRepositoryImpl", e.message.toString())
        }
    }

}

sealed class TaskException(message: String, cause: Throwable? = null): Exception(message, cause) {
    class InsertionError(message: String, cause: Throwable?): TaskException(message, cause)
}