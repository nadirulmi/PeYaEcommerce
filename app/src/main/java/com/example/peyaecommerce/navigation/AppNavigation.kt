package com.example.peyaecommerce.navigation

import BottomBar
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.peyaecommerce.view.ui.views.CartScreen
import com.example.peyaecommerce.view.ui.views.LoginScreen
import com.example.peyaecommerce.view.ui.views.ProductCatalogScreen
import com.example.peyaecommerce.view.ui.views.ProfileScreen
import com.example.peyaecommerce.view.ui.views.RegisterScreen
import com.example.peyaecommerce.view.viewmodel.ProductListViewModel
import com.example.peyaecommerce.view.viewmodel.ProfileViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.peyaecommerce.view.ui.views.OrderHistoryScreen
import com.example.peyaecommerce.view.ui.views.SplashScreen
import com.example.peyaecommerce.view.viewmodel.CartViewModel

object Routes {
    const val SPLASH = "splash"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home"
    const val CART = "cart"
    const val PROFILE = "profile"
    const val ORDER_HISTORY = "order_history"
}

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    val productListViewModel: ProductListViewModel = viewModel()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentRoute = navBackStackEntry?.destination?.route
    val showBottomBarRoutes = listOf(Routes.HOME, Routes.CART, Routes.PROFILE, Routes.ORDER_HISTORY)
    val cartViewModel: CartViewModel = hiltViewModel()

    Scaffold(
        bottomBar = {
            if (currentRoute in showBottomBarRoutes) {
                BottomBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Routes.SPLASH,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.SPLASH) {
                SplashScreen(
                    onAnimationFinished = {
                        navController.navigate(Routes.LOGIN) {
                            popUpTo(Routes.SPLASH) { inclusive = true }
                        }
                    }
                )
            }
            composable(Routes.LOGIN) { LoginScreen(navController) }
            composable(Routes.REGISTER) { RegisterScreen(navController) }
            composable(Routes.HOME) {
                ProductCatalogScreen(navController, cartViewModel = cartViewModel)
            }
            composable(Routes.CART) {
                CartScreen(navController, cartViewModel = cartViewModel)
            }
            composable(Routes.PROFILE) { backStackEntry ->
                val profileViewModel: ProfileViewModel = hiltViewModel(backStackEntry)
                ProfileScreen(
                    navController = navController,
                    profileViewModel = profileViewModel
                )
            }
            composable(Routes.ORDER_HISTORY) {
                OrderHistoryScreen(navController, hiltViewModel())
            }
        }
    }
}

