package com.taio.taio.data.model

import com.google.gson.annotations.SerializedName

data class Errors (
    @SerializedName("email")
    val email: List<String>,
    @SerializedName("password")
    val password: List<String>
)