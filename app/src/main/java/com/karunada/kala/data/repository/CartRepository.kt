package com.karunada.kala.data.repository

import com.karunada.kala.data.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor() {
    private val _cartItems = MutableStateFlow<List<Product>>(emptyList())
    val cartItems = _cartItems.asStateFlow()

    fun addToCart(product: Product) {
        _cartItems.value = _cartItems.value + product
    }

    fun removeFromCart(product: Product) {
        _cartItems.value = _cartItems.value.filter { it.id != product.id }
    }

    fun clearCart() {
        _cartItems.value = emptyList()
    }

    val totalPrice: Double
        get() = _cartItems.value.sumOf { it.price }
}
