package com.taio.taio.data

data class CreateState(
    val documentName: String = "",
    val documentDate: String = "",
    val documentNumber: String = "",
    val documentDescription: String = "",
    val isFormError: Boolean = false,
)
