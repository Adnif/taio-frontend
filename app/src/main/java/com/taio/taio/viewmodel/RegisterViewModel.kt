package com.taio.taio.viewmodel

import androidx.lifecycle.ViewModel
import com.taio.taio.data.PasswordRequirement
import com.taio.taio.data.RegisterState
import kotlinx.coroutines.flow.MutableStateFlow

class RegisterViewModel: ViewModel() {
    val registerState = MutableStateFlow(RegisterState())

    fun onNameChange(name: String){
        registerState.value = registerState.value.copy(name = name)
    }

    fun onUserNameChange(userName: String){
        registerState.value = registerState.value.copy(userName = userName)
    }

    fun onEmailChange(email: String){
        registerState.value = registerState.value.copy(email = email)
    }

    fun onPhoneChange(phone: String){
        registerState.value = registerState.value.copy(phone = phone)
    }

    fun onPasswordChange(password: String){
        val requirement = mutableListOf<PasswordRequirement>()

        if(password.length > 7){
            requirement.add(PasswordRequirement.EIGHT_CHARACTERS)
        }
        if(password.any { it.isUpperCase() }){
            requirement.add(PasswordRequirement.CAPITAL_LETTER)
        }
        if(password.any { it.isDigit() }){
            requirement.add(PasswordRequirement.NUMBER)
        }
        registerState.value = registerState.value.copy(password = password, passwordRequirement = requirement.toList())
    }

    fun onPassConfirmChange(passConfirm: String){
        registerState.value = registerState.value.copy(passConfirm = passConfirm)
    }

    fun isVisible(){
        registerState.value = registerState.value.copy(passwordVisibility = !registerState.value.passwordVisibility)
    }

    fun onCheckBoxChange(){
        registerState.value = registerState.value.copy(checkBox = !registerState.value.checkBox)
    }

    fun isFormError(error: Boolean){
        registerState.value = registerState.value.copy(isFormError = error)
    }

    fun isPasswordMatch():Boolean{
        return registerState.value.password == registerState.value.passConfirm
    }
    fun isPassMetRequirement():Boolean{
        return registerState.value.passwordRequirement.containsAll(PasswordRequirement.values().toList())
    }
    fun isPageOneValid():Boolean{
        return (registerState.value.name.isNotEmpty() && registerState.value.userName.isNotEmpty())
    }

    fun isPageTwoValid():Boolean{
        return (registerState.value.email.isNotEmpty() && registerState.value.phone.isNotEmpty())
    }

    fun isPageThreeValid():Boolean{
        return (registerState.value.password.isNotEmpty() && registerState.value.passConfirm.isNotEmpty())
    }

    fun isLastPageValid():Boolean{
        return registerState.value.checkBox
    }


}