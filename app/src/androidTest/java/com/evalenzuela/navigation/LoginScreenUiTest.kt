package com.evalenzuela.navigation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.evalenzuela.navigation.ui.screens.LoginScreen
import org.junit.Rule
import org.junit.Test

class LoginScreenUiTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun loginScreen_displaysFields() {
        rule.setContent { LoginScreen({}, {}) }

        rule.onNodeWithText("Iniciar Sesión").assertExists()
        rule.onNodeWithText("Email").assertExists()
        rule.onNodeWithText("Contraseña").assertExists()
    }
}