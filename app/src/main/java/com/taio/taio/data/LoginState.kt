package com.taio.taio.data

data class LoginState (
    val email: String = "",
    val password: String = "",
    val passwordVisibility: Boolean = true,
    val isFormError: Boolean = false,
)