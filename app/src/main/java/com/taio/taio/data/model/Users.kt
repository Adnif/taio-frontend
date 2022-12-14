package com.taio.taio.data.model

import com.google.gson.annotations.SerializedName

data class Users(
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("email")
    val email:String,
    @SerializedName("email_verified_at")
    val emailVerifiedAt: Any,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("updated_at")
    val updatedAt: String

)
