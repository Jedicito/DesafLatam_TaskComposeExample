package com.example.taskcomposeexample.di

import android.content.Context
import androidx.room.Room
import com.example.taskcomposeexample.data.local.TaskDao
import com.example.taskcomposeexample.data.local.TaskDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): TaskDataBase {
        return Room.databaseBuilder(
            context = context,
            TaskDataBase::class.java,
            "Task_database"
        ).build()
    }

    @Provides
    fun provideTaskDao(dataBase: TaskDataBase): TaskDao {
        return dataBase.taskDao()
    }

}