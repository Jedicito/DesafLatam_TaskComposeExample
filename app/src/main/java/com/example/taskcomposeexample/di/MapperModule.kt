package com.example.taskcomposeexample.di

import com.example.taskcomposeexample.data.mapper.TaskMapper
import com.example.taskcomposeexample.presentation.mapper.TaskUiMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {

    @Provides
    fun provideTaskMapper(): TaskMapper {
        return TaskMapper()
    }

    @Provides
    fun provideTaskUiMapper(): TaskUiMapper {
        return TaskUiMapper()
    }
}