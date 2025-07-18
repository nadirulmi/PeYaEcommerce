import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ReceiptLong
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Receipt
import androidx.compose.material.icons.outlined.ReceiptLong
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.peyaecommerce.navigation.Routes

@Composable
fun BottomBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        Triple(Icons.Outlined.Home, Routes.HOME, "Inicio"),
        Triple(Icons.Outlined.ShoppingCart, Routes.CART, "Carrito"),
        Triple(Icons.Outlined.Person, Routes.PROFILE, "Perfil"),
        Triple(Icons.Outlined.Receipt, Routes.ORDER_HISTORY, "Historial")
    )

    NavigationBar(
        containerColor = Color(0xFFF9F9F9),
        tonalElevation = 0.dp,
        modifier = Modifier.height(65.dp)
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
                icon = {
                    Icon(
                        imageVector = item.first,
                        contentDescription = item.third,
                        modifier = Modifier.size(22.dp) // Ícono más chico
                    )
                },
                label = {
                    Text(
                        text = item.third,
                        fontSize = 12.sp, // Texto más compacto
                        fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF4A0D22),
                    selectedTextColor = Color(0xFF4A0D22),
                    unselectedIconColor = Color(0xFF5F5F5F),
                    unselectedTextColor = Color(0xFF5F5F5F),
                    indicatorColor = Color(0x14FF0000) // Color con transparencia (círculo suave)
                )
            )
        }
    }
}



