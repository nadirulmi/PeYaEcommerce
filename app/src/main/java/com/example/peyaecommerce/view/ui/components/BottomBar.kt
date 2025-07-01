import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.peyaecommerce.navigation.Routes

@Composable
fun BottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        Triple(Icons.Filled.Home, Routes.HOME, "Inicio"),
        Triple(Icons.Filled.ShoppingCart, Routes.CART, "Carrito"),
        Triple(Icons.Filled.Person, Routes.PROFILE, "Perfil"),
        Triple(Icons.Filled.ShoppingBag, Routes.ORDER_HISTORY, "Historial")
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 8.dp
    ) {
        items.forEach { item ->
            val selected = currentRoute == item.second
            NavigationBarItem(
                selected = selected,
                onClick = {
                    if (currentRoute != item.second) {
                        navController.navigate(item.second) {
                            popUpTo(Routes.HOME) { saveState = true }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = { Icon(item.first, contentDescription = item.third) },
                label = { Text(item.third) }
            )
        }
    }
}

