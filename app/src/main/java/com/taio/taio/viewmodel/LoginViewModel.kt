package com.taio.taio.viewmodel

import androidx.lifecycle.ViewModel
import com.taio.taio.data.LoginState
import kotlinx.coroutines.flow.MutableStateFlow

class LoginViewModel: ViewModel() {
    val loginState = MutableStateFlow(LoginState())

    fun updateEmail(email: String){
        loginState.value = loginState.value.copy(email = email)
    }

    fun updatePassword(password: String){
        loginState.value = loginState.value.copy(password = password)
    }

    fun isVisible(){
        loginState.value = loginState.value.copy(passwordVisibility = !loginState.value.passwordVisibility)
    }

    fun isFormError(error: Boolean){
        loginState.value = loginState.value.copy(isFormError = error)
    }

    fun isFormValid(): Boolean{
        return (loginState.value.email.isNotEmpty() && loginState.value.password.isNotEmpty())
    }
    
}