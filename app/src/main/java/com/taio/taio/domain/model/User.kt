package com.taio.taio.domain.model

import androidx.annotation.DrawableRes

data class User(
    @DrawableRes val avatar: Int,
    val name: String,
    val email: String = "example@gmail.com"
)
