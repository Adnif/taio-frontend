package com.taio.taio.data.model

import com.google.gson.annotations.SerializedName

data class ErrorResponse (
    @SerializedName("errors")
    var errors: Map<String, List<String>>? = null,
    @SerializedName("message")
    var message: String? = null

)