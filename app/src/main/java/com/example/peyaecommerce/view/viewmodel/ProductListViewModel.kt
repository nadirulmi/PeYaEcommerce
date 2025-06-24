package com.example.peyaecommerce.view.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.peyaecommerce.data.model.Product
import com.example.peyaecommerce.data.repository.ProductRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class CartItem(
    val product: Product,
    var quantity: Int
)

class ProductListViewModel : ViewModel() {

    var searchQuery by mutableStateOf("")
    var selectedCategory by mutableStateOf("Todos")
    var priceOrder by mutableStateOf("Ninguno")
    private val allProducts = ProductRepository.products
    var filteredProducts by mutableStateOf(allProducts)
        private set

    // NUEVO: Carrito
    var cartItems by mutableStateOf(listOf<CartItem>())
        private set

    fun addToCart(product: Product) {
        Log.d("Carrito", "Llego el producto: ${product}")

        val existingItem = cartItems.find { it.product == product }
        cartItems = if (existingItem != null) {
            cartItems.map {
                if (it.product == product) it.copy(quantity = it.quantity + 1) else it
            }
        } else {
            cartItems + CartItem(product, 1)
        }
    }

    fun increaseQuantity(product: Product) {
        viewModelScope.launch {
            delay(300)
            cartItems = cartItems.map {
                if (it.product == product) it.copy(quantity = it.quantity + 1) else it
            }
        }
    }

    fun decreaseQuantity(product: Product) {
        viewModelScope.launch {
            delay(300)
            cartItems = cartItems.mapNotNull {
                if (it.product == product) {
                    if (it.quantity > 1) it.copy(quantity = it.quantity - 1)
                    else null
                } else it
            }
        }
    }

    fun removeFromCart(product: Product) {
        viewModelScope.launch {
            delay(300)
            cartItems = cartItems.filter { it.product != product }
        }
    }

    init {
        filterProducts()
    }

    fun onSearchQueryChange(query: String) {
        searchQuery = query
        filterProducts()
    }

    fun onCategorySelected(category: String) {
        selectedCategory = category
        filterProducts()
    }

    fun onPriceOrderSelected(order: String) {
        priceOrder = order
        filterProducts()
    }

    private fun filterProducts() {
        var result = allProducts.filter {
            it.nombre.contains(searchQuery, ignoreCase = true)
        }

        if (selectedCategory != "Todos") {
            result = result.filter { it.categoria == selectedCategory }
        }

        result = when (priceOrder) {
            "Ascendente" -> result.sortedBy { it.precio }
            "Descendente" -> result.sortedByDescending { it.precio }
            else -> result
        }

        filteredProducts = result
    }
}
