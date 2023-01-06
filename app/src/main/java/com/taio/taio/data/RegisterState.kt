package com.taio.taio.data

data class RegisterState(
    val name: String = "",
    val userName: String = "",
    val email: String = "",
    val phone: String = "",
    val password: String = "",
    val passConfirm: String = "",
    val passwordRequirement: List<PasswordRequirement> = emptyList(),
    val passwordVisibility: Boolean = true,
    val checkBox: Boolean = false,
    val isFormError: Boolean = false,
)
enum class PasswordRequirement(){
    CAPITAL_LETTER,NUMBER,EIGHT_CHARACTERS
}