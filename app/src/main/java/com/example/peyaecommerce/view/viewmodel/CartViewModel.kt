package com.example.peyaecommerce.view.viewmodel

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.peyaecommerce.model.models.CartItem
import com.example.peyaecommerce.model.models.Product
import com.example.peyaecommerce.model.database.entities.OrderEntity
import com.example.peyaecommerce.model.database.entities.OrderItemEntity
import com.example.peyaecommerce.model.database.ProductDataBase
import com.example.peyaecommerce.model.database.mappers.toEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val database: ProductDataBase,
    val myApp: Application,
) : AndroidViewModel(myApp) {

    private val cartDao = database.cartDao()
    private val orderDao = database.orderDao()
    var cartItems by mutableStateOf(listOf<CartItem>())
        private set

    val totalAmount: Double
        get() = cartItems.sumOf { it.product.price * it.quantity }

    fun startCollectingCart() {
        viewModelScope.launch {
            cartDao.getAllItems().collect { entities ->
                cartItems = entities.map { entity ->
                    CartItem(
                        product = Product(
                            id = entity.productId,
                            name = entity.nombre,
                            category = entity.categoria,
                            price = entity.precio,
                            imageResId = entity.imagenResId,
                            imageUrl = entity.imageUrl
                        ),
                        quantity = entity.cantidad
                    )
                }
            }
        }
    }

    fun addToCart(product: Product) {
        val existingItem = cartItems.find { it.product == product }
        if (existingItem != null) {
            cartItems = cartItems.map {
                if (it.product == product) it.copy(quantity = it.quantity + 1) else it
            }
        } else {
            val newItem = CartItem(product, 1)
            cartItems = cartItems + newItem
            viewModelScope.launch {
                cartDao.insert(newItem.toEntity())
            }
        }
    }

    fun removeFromCart(product: Product) {
        viewModelScope.launch {
            cartDao.deleteByProductId(product.id ?: "")
            cartItems = cartItems.filter { it.product != product }
        }
    }

    fun clearCart() {
        cartItems = emptyList()
    }

    fun updateQuantity(product: Product, newQuantity: Int) {
        cartItems = cartItems.map {
            if (it.product == product) it.copy(quantity = newQuantity) else it
        }.filter { it.quantity > 0 }
    }

    fun checkout() {
        viewModelScope.launch {
            if (cartItems.isNotEmpty()) {
                val total = totalAmount
                val order = OrderEntity(
                    date = System.currentTimeMillis(),
                    total = total
                )
                val orderId = orderDao.insertOrder(order).toInt()

                val items = cartItems.map { cartItem ->
                    OrderItemEntity(
                        orderId = orderId,
                        productId = cartItem.product.id ?: "",
                        nombre = cartItem.product.name,
                        precio = cartItem.product.price,
                        cantidad = cartItem.quantity,
                        imagenResId = cartItem.product.imageResId
                    )
                }
                orderDao.insertOrderItems(items)

                cartDao.clearCart()
                clearCart()
            }
        }
    }

}




