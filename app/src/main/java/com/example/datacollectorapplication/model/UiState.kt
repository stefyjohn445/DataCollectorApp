package com.example.datacollectorapplication.model

sealed class UiState<T>{
    data class Loading<T>(val isLoading: Boolean): UiState<T>()
    data class Success<T>(val result: T): UiState<T>()
    data class Error<T>(val message: String): UiState<T>()
}