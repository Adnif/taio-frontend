package com.taio.taio.data.model

data class RegisterRequest(
    val name: String,
    val userName: String,
    val userId: String,
    val password: String,
    val password_confirmation: String
)
