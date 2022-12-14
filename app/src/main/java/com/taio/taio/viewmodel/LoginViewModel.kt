package com.taio.taio.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.taio.taio.data.LoginState
import com.taio.taio.data.model.ErrorResponse
import com.taio.taio.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import com.taio.taio.data.utils.Result
import javax.inject.Inject

@HiltViewModel
class LoginViewModel
@Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {
    var loginState = MutableStateFlow(LoginState())


    private val resultChannel = Channel<Result<out Any?>>()
    val authResults = resultChannel.receiveAsFlow()

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

    private fun login(){
        viewModelScope.launch{
            loginState.value = loginState.value.copy(isLoading = true)

            val result: Result<out Any> = repository.login(
                email = loginState.value.email,
                password = loginState.value.password
            )

            resultChannel.send(result)

            loginState.value = loginState.value.copy(isLoading = false)
        }
    }

    fun validate(apiResponse: ErrorResponse?){
        apiResponse?.errors?.forEach{ error ->
            when(error.key){
                "email" -> {
                    loginState.value = loginState.value.copy(emailError = error.value[0])
                }

                "password" -> {
                    loginState.value = loginState.value.copy(passwordError = error.value[0])
                }
            }
        }
    }

}