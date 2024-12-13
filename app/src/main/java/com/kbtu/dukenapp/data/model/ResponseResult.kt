package com.kbtu.dukenapp.data.model

sealed class ResponseResult<out T> {
    data class Success<out T>(val data: T) : ResponseResult<T>()
    data class Error(val exception: Exception) : ResponseResult<Nothing>()
}