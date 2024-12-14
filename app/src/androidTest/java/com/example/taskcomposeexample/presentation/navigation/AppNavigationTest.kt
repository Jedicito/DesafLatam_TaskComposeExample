package com.example.taskcomposeexample.presentation.navigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.NavHostController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.taskcomposeexample.presentation.viewmodel.TaskViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class AppNavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: NavHostController

    @Test
    fun testNavigation() {
        composeTestRule.setContent {
            navController = TestNavHostController(ApplicationProvider.getApplicationContext())
            AppNavigation(viewModel = TaskViewModel())
        }

        // Verificar destino inicial
        composeTestRule.onNodeWithText("Añadir").assertIsDisplayed()

        // Simular navegación
        composeTestRule
            .onNodeWithText("Añadir")
            .performClick()
        composeTestRule.onNodeWithTag("column").assertIsDisplayed()
    }
}