package com.evalenzuela.navigation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.evalenzuela.navigation.data.model.Favorite
import com.evalenzuela.navigation.ui.screens.FavoriteScreen
import org.junit.Rule
import org.junit.Test

class FavoriteScreenUiTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun favoriteScreen_showsMessage() {
        val testViewModel = FavoriteViewModel()
        rule.setContent {
            FavoriteScreen(favoriteViewModel = testViewModel)
        }

        rule.onNodeWithText("Tus recetas favoritas").assertExists()
    }

}

