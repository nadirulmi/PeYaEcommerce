package com.example.peyaecommerce.view.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.peyaecommerce.model.CartItem
import com.example.peyaecommerce.model.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    val myApp: Application,
) : AndroidViewModel(myApp) {

    var cartItems by mutableStateOf(listOf<CartItem>())
        private set

    val totalAmount: Double
        get() = cartItems.sumOf { it.product.precio * it.quantity }


    fun addToCart(product: Product) {
        val existingItem = cartItems.find { it.product == product }
        cartItems = if (existingItem != null) {
            cartItems.map {
                if (it.product == product) it.copy(quantity = it.quantity + 1) else it
            }
        } else {
            cartItems + CartItem(product, 1)
        }
    }

    fun removeFromCart(product: Product) {
        cartItems = cartItems.filter { it.product != product }
    }

    fun clearCart() {
        cartItems = emptyList()
    }

    fun updateQuantity(product: Product, newQuantity: Int) {
        cartItems = cartItems.map {
            if (it.product == product) it.copy(quantity = newQuantity) else it
        }.filter { it.quantity > 0 }
    }
}




