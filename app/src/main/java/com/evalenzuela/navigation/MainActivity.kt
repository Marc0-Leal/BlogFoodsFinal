package com.evalenzuela.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.evalenzuela.navigation.data.PreferencesManager
import com.evalenzuela.navigation.navigation.BottomBar
import com.evalenzuela.navigation.navigation.BottomNavItem
import com.evalenzuela.navigation.navigation.Routes
import com.evalenzuela.navigation.ui.screens.*
import com.evalenzuela.navigation.ui.theme.NavigationTheme
import com.evalenzuela.navigation.ui.viewmodel.FavoriteViewModel
import com.evalenzuela.navigation.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationTheme {
                App(preferences = PreferencesManager(this))
            }
        }
    }
}

@Composable
fun App(preferences: PreferencesManager) {
    val navController = rememberNavController()
    val bottomItems = listOf(BottomNavItem.Home, BottomNavItem.Profile, BottomNavItem.Favorite)

    val mainViewModel: MainViewModel = viewModel()

    val isLoggedIn = remember { mutableStateOf(preferences.isLoggedIn()) }
    val startDestination = if (isLoggedIn.value) Routes.HOME else Routes.LOGIN

    val favoriteViewModel: FavoriteViewModel = viewModel()

    Scaffold(
        bottomBar = {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            if (currentRoute != Routes.LOGIN && currentRoute != Routes.REGISTER) {
                BottomBar(navController, bottomItems)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.LOGIN) {
                LoginScreen(
                    onLoginSuccess = {
                        preferences.saveLoginState(true)
                        navController.navigate(Routes.HOME) {
                            popUpTo(Routes.LOGIN) { inclusive = true }
                        }
                    },
                    onNavigateToRegister = {
                        navController.navigate(Routes.REGISTER)
                    }
                )
            }

            composable(Routes.REGISTER) {
                RegisterScreen(
                    onRegisterSuccess = {
                        navController.navigate(Routes.LOGIN) {
                            popUpTo(Routes.REGISTER) { inclusive = true }
                        }
                    },
                    onNavigateBack = {
                        navController.popBackStack()
                    }
                )
            }

            composable(Routes.HOME) {


                HomeScreen(
                    favoriteViewModel = favoriteViewModel,
                    mainViewModel = mainViewModel,
                    navController = navController
                )
            }

            composable(Routes.ADD_RECIPE) {
                AddRecipeScreen(
                    navController = navController,
                    mainViewModel = mainViewModel
                )
            }

            composable(Routes.FAVORITE) {
                FavoriteScreen(favoriteViewModel)
            }

            composable(Routes.PROFILE) {
                ProfileScreen(navController = navController)
            }

            composable(
                route = Routes.DETAIL,
                arguments = listOf(navArgument("itemId") { type = NavType.IntType })
            ) { backStackEntry ->
                val vm: MainViewModel = viewModel()
                val id = backStackEntry.arguments?.getInt("itemId") ?: -1
                DetailScreen(
                    itemId = id,
                    viewModel = vm,
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
