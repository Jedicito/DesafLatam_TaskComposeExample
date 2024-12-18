package com.example.taskcomposeexample.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [TaskEntity::class],
    version = 1,
    exportSchema = true
)
abstract class TaskDataBase: RoomDatabase() {
    abstract fun taskDao(): TaskDao
}