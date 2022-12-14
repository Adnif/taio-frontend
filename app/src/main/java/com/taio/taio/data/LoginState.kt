package com.taio.taio.data

data class LoginState (
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = true,
    val isFormError: Boolean = false,
    val emailError: String = "",
    val passwordError: String = ""
)