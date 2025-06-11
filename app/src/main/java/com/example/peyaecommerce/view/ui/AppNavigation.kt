package com.example.peyaecommerce.view.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.peyaecommerce.view.ui.views.LoginScreen
import com.example.peyaecommerce.view.ui.views.ProductCatalogScreen
import com.example.peyaecommerce.view.ui.views.RegisterScreen


@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") { LoginScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") {
            ProductCatalogScreen(navController = navController)
        }
    }
}
