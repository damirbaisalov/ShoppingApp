package com.kbtu.dukenapp.domain.model

data class User(
    var id: Int = UNDEFINED_ID,
    val email: String,
    var name: String,
    var password: String
) {
    companion object {
        const val UNDEFINED_ID = 0
    }
}
