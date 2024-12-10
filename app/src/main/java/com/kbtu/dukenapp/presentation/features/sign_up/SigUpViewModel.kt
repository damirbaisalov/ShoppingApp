package com.kbtu.dukenapp.presentation.features.sign_up

import com.kbtu.dukenapp.presentation.mvi.BaseViewModel

class SigUpViewModel :
    BaseViewModel<SigUpContract.State, SigUpContract.Event, SigUpContract.Effect>() {

    override fun createInitialState(): SigUpContract.State {
        return SigUpContract.State
    }

    override fun handleEvent(event: SigUpContract.Event) {

    }
}