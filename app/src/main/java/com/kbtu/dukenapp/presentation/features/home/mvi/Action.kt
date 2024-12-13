package com.kbtu.dukenapp.presentation.features.home.mvi

import com.common.mvi.BaseAction
import com.kbtu.dukenapp.presentation.model.CategoryUiModel

sealed class Action: BaseAction() {

    class SetLoadingState(val isLoading: Boolean): Action()
    class SetCategories(val categories: List<CategoryUiModel>): Action()
    class SetScreenState(val screenState: ScreenState): Action()
}