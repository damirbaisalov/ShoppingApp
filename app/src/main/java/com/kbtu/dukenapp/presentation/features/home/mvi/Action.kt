package com.kbtu.dukenapp.presentation.features.home.mvi

import com.common.mvi.BaseAction
import com.kbtu.dukenapp.presentation.model.CategoryUiModel
import com.kbtu.dukenapp.presentation.model.ProductUiModel

sealed class Action: BaseAction() {

    class SetLoadingState(val isLoading: Boolean): Action()
    class SetCategories(val categories: List<CategoryUiModel>): Action()
    class SetScreenState(val screenState: ScreenState): Action()
    class SetCategorySelection(val categoryId: Int): Action()
    class SetCartItems(val cartItems: List<ProductUiModel>) : Action()
}