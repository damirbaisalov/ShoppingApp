package com.kbtu.dukenapp.presentation.features.home.mvi

import com.common.mvi.BaseReducer

class Reducer : BaseReducer<State, Action>() {

    override fun reduce(state: State, action: Action): State =
        when (action) {
            is Action.SetScreenState -> setScreenState(state, action)
            is Action.SetLoadingState -> state.copy(
                screenState = ScreenState.Loading,
                isLoading = true,
                products = emptyList(),
                categories = emptyList()
            )

            is Action.SetCategories -> state.copy(
                categories = action.categories
            )

            is Action.SetCategorySelection -> setCategorySelection(state, action)
            is Action.SetCartItems -> setCartItems(state, action)
        }

    private fun setScreenState(state: State, action: Action.SetScreenState): State {
        val products = if (action.screenState is ScreenState.SuccessLoad) {
            action.screenState.data
        } else emptyList()

        return state.copy(
            screenState = action.screenState,
            products = products,
            isLoading = false
        )
    }

    private fun setCategorySelection(state: State, action: Action.SetCategorySelection): State {
        val currentCategories = state.categories.toMutableList()

        if (state.screenState !is ScreenState.SuccessLoad) return state

        val updatedCategories = currentCategories.map { category ->
            if (category.id == action.categoryId) {
                category.copy(isSelected = !category.isSelected)
            } else {
                category
            }
        }

        val filteredProducts = if (updatedCategories.any { it.isSelected }) {
            state.products.filter { product ->
                updatedCategories.any { category ->
                    category.isSelected && product.category.id == category.id
                }
            }
        } else {
            state.products
        }

        return state.copy(
            categories = updatedCategories, // Updated categories
            screenState = ScreenState.SuccessLoad(data = filteredProducts) // Filtered products
        )
    }

    private fun setCartItems(state: State, action: Action.SetCartItems): State {
        val screenState = state.screenState
        if (screenState !is ScreenState.SuccessLoad) {
            return state
        }
        val updatedCart = action.cartItems
        val updatedProducts = screenState.data.map { product ->
            val cartItem = updatedCart.find { it.productId == product.productId }
            product.copy(count = cartItem?.count ?: 0)
        }

        return state.copy(
            cart = updatedCart,
            products = updatedProducts,
            screenState = ScreenState.SuccessLoad(updatedProducts) // Ensure correct screen state
        )
    }
}