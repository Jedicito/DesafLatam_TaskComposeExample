package com.example.taskcomposeexample.di

import com.example.taskcomposeexample.domain.repository.TaskRepository
import com.example.taskcomposeexample.domain.usecase.AddTaskUseCase
import com.example.taskcomposeexample.domain.usecase.DeleteTaskUseCase
import com.example.taskcomposeexample.domain.usecase.GetTaskUseCase
import com.example.taskcomposeexample.domain.usecase.UpdateTaskUseCase
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

    @Provides
    fun provideUpdateTaskUseCase(repository: TaskRepository) : UpdateTaskUseCase {
        return UpdateTaskUseCase(repository)
    }

    @Provides
    fun provideDeleteUseCase(repository: TaskRepository) : DeleteTaskUseCase {
        return DeleteTaskUseCase(repository)
    }

}