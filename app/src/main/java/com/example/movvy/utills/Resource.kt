package com.example.movvy.utills

sealed class Result<out T>() {
    class Success<out T>(val data: T) : Result<T>()
    class Error(val exception: Throwable) : Result<Nothing>()
    class Loading():Result<Nothing>()
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}