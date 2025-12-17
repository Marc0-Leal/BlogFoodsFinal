package com.evalenzuela.navigation

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import com.evalenzuela.navigation.ui.screens.ProfileScreen
import org.junit.Rule
import org.junit.Test

class ProfileScreenUiTest {

    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun profileScreen_showsButtons() {
        composeRule.setContent {
            val navController = rememberNavController()
            ProfileScreen(navController = navController)
        }

        composeRule.onNodeWithText("📷 Tomar Foto").assertExists()
        composeRule.onNodeWithText("🖼️ Seleccionar Imagen").assertExists()
        composeRule.onNodeWithText("📁 Seleccionar Archivo").assertExists()
    }
}

