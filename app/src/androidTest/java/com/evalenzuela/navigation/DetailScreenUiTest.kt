package com.evalenzuela.navigation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.evalenzuela.navigation.ui.screens.DetailScreen
import com.evalenzuela.navigation.ui.viewmodel.MainViewModel
import org.junit.Rule
import org.junit.Test

class DetailScreenUiTest {

    @get:Rule
    val rule = createComposeRule()

    @Test
    fun detailScreen_showsItemInfo() {
        val vm = MainViewModel()

        rule.setContent {
            DetailScreen(
                itemId = 1,
                viewModel = vm,
                onBack = {}
            )
        }

        rule.onNodeWithText(vm.getItem(1)!!.title).assertExists()
    }
}