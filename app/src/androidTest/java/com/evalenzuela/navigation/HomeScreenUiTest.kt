package com.evalenzuela.navigation

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.proyectoblog.ui.screens.HomeScreen
import org.junit.Rule
import org.junit.Test

class HomeScreenUiTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun homeScreen_showsTitleAndSearchField() {
        composeRule.setContent { HomeScreen() }

        composeRule.onNodeWithText("Blog de comida").assertExists()
        composeRule.onNodeWithText("Búsqueda de comida").assertExists()
    }
}