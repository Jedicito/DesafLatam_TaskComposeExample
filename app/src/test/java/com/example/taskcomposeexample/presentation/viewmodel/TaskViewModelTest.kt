package com.example.taskcomposeexample.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.runtime.livedata.observeAsState
import com.example.taskcomposeexample.presentation.uimodel.TaskUIState
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class TaskViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: TaskViewModel

    @Before
    fun setup() {
        viewModel = TaskViewModel()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `state is success and task is a empty list when loading the viewmodel`() {
        //when
        val state = viewModel.state

        //then
        assertEquals(TaskUIState.Success(listOf()) , state.value)
    }



}
