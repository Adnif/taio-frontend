package com.taio.taio.data.model

import com.google.gson.annotations.SerializedName

data class Message(
    @SerializedName("message")
    val message: String
)
