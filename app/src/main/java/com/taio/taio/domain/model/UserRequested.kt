package com.taio.taio.domain.model

data class UserRequested(
    val avatar: Int,
    val title: String,
    val name: String,
    val desc: String,
    val status: Int,
    val rejectMessage: String = ""
)
