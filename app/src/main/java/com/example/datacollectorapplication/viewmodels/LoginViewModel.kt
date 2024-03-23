package com.example.datacollectorapplication.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.datacollectorapplication.model.UiState
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {
    private val _uiState = MutableLiveData<UiState<String>>()
    val uiState: LiveData<UiState<String>> = _uiState

    fun checkUserCredentials(username: String, password: String){
        _uiState.value = UiState.Loading(true)
        viewModelScope.launch {
            try{
                checkUserLogin(username, password)
            }catch (e: Exception){
                e.printStackTrace()
                _uiState.value = UiState.Loading(false)
                _uiState.value = UiState.Error(e.message ?: "Unknown Error")
            }
        }
    }

    private fun checkUserLogin(username: String, password: String) {
        if(username.isNotEmpty() && password.isNotEmpty()){
            _uiState.value = UiState.Success("Success")
            _uiState.value = UiState.Loading(false)
        }else{
            _uiState.value = UiState.Success("Failure")
            _uiState.value = UiState.Loading(false)
        }
    }
}
