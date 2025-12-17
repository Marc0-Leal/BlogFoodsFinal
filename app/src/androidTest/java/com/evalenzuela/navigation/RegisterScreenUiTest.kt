package com.evalenzuela.navigation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.evalenzuela.navigation.ui.screens.RegisterScreen
import org.junit.Rule
import org.junit.Test

class RegisterScreenUiTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun registerScreen_displaysAllInputs() {
        rule.setContent { RegisterScreen({}, {}) }

        rule.onNodeWithText("Crear Cuenta").assertExists()
        rule.onNodeWithText("Nombre").assertExists()
        rule.onNodeWithText("Correo electrónico").assertExists()
        rule.onNodeWithText("Confirmar contraseña").assertExists()
    }
}