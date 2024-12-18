package com.example.taskcomposeexample.di

import com.example.taskcomposeexample.domain.repository.TaskRepository
import com.example.taskcomposeexample.domain.usecase.AddTaskUseCase
import com.example.taskcomposeexample.domain.usecase.GetTaskUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetTaskUseCase(repository: TaskRepository) : GetTaskUseCase {
        return GetTaskUseCase(repository)
    }

    @Provides
    fun provideAddTaskUseCase(repository: TaskRepository) : AddTaskUseCase {
        return AddTaskUseCase(repository)
    }

}