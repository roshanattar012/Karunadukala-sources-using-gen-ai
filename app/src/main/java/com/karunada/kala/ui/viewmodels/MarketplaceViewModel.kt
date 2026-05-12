package com.karunada.kala.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.karunada.kala.data.Product
import com.karunada.kala.data.repository.CartRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MarketplaceViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {
    val cartItems = cartRepository.cartItems

    fun addToCart(product: Product) {
        cartRepository.addToCart(product)
    }
}
