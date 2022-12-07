package com.taio.taio.ui.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.taio.taio.R
import com.taio.taio.data.RegisterState
import com.taio.taio.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(viewModel: RegisterViewModel = viewModel()){
    val registerState = viewModel.registerState.collectAsState().value
    val page = remember { mutableStateOf(1) }
    val focusManager = LocalFocusManager.current
    BackHandler(enabled = true) {
        if(page.value == 1) {
            page.value = 1
        }else{
            page.value = page.value - 1
        }

    }
    when(page.value){
        1 -> FirstPage(
            viewModel = viewModel,
            registerState = registerState,
            page = page,
            focusManager = focusManager,
        )
        2 -> SecondPage(
            viewModel = viewModel,
            registerState = registerState,
            page = page,
            focusManager = focusManager,
        )
        3 -> ThirdPage(
            viewModel = viewModel,
            registerState = registerState,
            page = page,
            focusManager = focusManager,
        )
        else -> LastPage(
            viewModel = viewModel,
            registerState = registerState,
            page = page,
            focusManager = focusManager,
        )

    }

}


@Composable
fun FirstPage(
    viewModel: RegisterViewModel,
    registerState: RegisterState,
    page: MutableState<Int>,
    focusManager: FocusManager,
){
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),

    ){
        Text(
            text = "Welcome To",
            fontSize = 26.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
        Image(
            painter = painterResource(id = R.drawable.logo_taio),
            contentDescription = "Logo Taio",
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.size(16.dp))
        TextFields(
            label = "Nama Lengkap",
            text = registerState.name,
            onValueChange = {name -> viewModel.onNameChange(name)},
            isError = {error -> viewModel.isFormError(error) },
            errorState = registerState.isFormError,
            leadIcon = R.drawable.profile_bold,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
        Spacer(Modifier.size(16.dp))
        TextFields(
            label = "Username",
            text = registerState.userName,
            onValueChange = {userName -> viewModel.onUserNameChange(userName)},
            isError = {error -> viewModel.isFormError(error) },
            errorState = registerState.isFormError,
            leadIcon = R.drawable.username,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
        Spacer(Modifier.size(16.dp))
        Spacer(Modifier.weight(1f))
        Footer(
            label = "Next",
            onButtonClick = {
                if(!viewModel.isPageOneValid()) {
                    viewModel.isFormError(true)
                }else{
                    page.value = 2
                }
            }
        )
    }


}

@Composable
fun SecondPage(
    viewModel: RegisterViewModel,
    registerState: RegisterState,
    page: MutableState<Int>,
    focusManager: FocusManager,
){
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ){
        TextFields(
            label = "Email",
            text = registerState.email,
            onValueChange = {email -> viewModel.onEmailChange(email)},
            isError = {error -> viewModel.isFormError(error) },
            errorState = registerState.isFormError,
            leadIcon = R.drawable.profile_bold,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
        Spacer(Modifier.size(16.dp))
        TextFields(
            label = "Phone Number",
            text = registerState.phone,
            onValueChange = {phone -> viewModel.onPhoneChange(phone)},
            isError = {error -> viewModel.isFormError(error) },
            errorState = registerState.isFormError,
            leadIcon = R.drawable.username,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
        Spacer(Modifier.size(16.dp))
        Spacer(Modifier.weight(1f))
        Footer(
            label = "Next",
            onButtonClick = {
                if(!viewModel.isPageTwoValid()) {
                    viewModel.isFormError(true)
                }else{
                    page.value = 3
                }
            }
        )
    }

}

@Composable
fun ThirdPage(
    viewModel: RegisterViewModel,
    registerState: RegisterState,
    page: MutableState<Int>,
    focusManager: FocusManager,
){
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ){
        PassField(
            label = "Password",
            text = registerState.password,
            errorState = registerState.isFormError,
            onValueChange = {password -> viewModel.onPasswordChange(password)},
            onViewClick = { viewModel.isVisible() },
            isError = {error -> viewModel.isFormError(error)},
            passwordVisibility = registerState.passwordVisibility,
            leadIcon = R.drawable.password,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
        Spacer(Modifier.size(16.dp))
        PassField(
            label = "Password Confirm",
            text = registerState.passConfirm,
            errorState = registerState.isFormError,
            onValueChange = {password -> viewModel.onPassConfirmChange(password)},
            onViewClick = { viewModel.isVisible() },
            isError = {error -> viewModel.isFormError(error)},
            passwordVisibility = registerState.passwordVisibility,
            leadIcon = R.drawable.password_confirm,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
        Spacer(Modifier.size(16.dp))
        Spacer(Modifier.weight(1f))
        Footer(
            label = "Next",
            onButtonClick = {

                if(!viewModel.isPageThreeValid() || !viewModel.isPasswordMatch() || !viewModel.isPassMetRequirement()) {
                    viewModel.isFormError(true)
                }else{
                    page.value = 4
                }
            }
        )
    }

}

@Composable
fun LastPage(
    viewModel: RegisterViewModel,
    registerState: RegisterState,
    page: MutableState<Int>,
    focusManager: FocusManager,
){
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
    ){
        TextFields(
            label = "Nama Lengkap",
            text = registerState.name,
            enabled = false,
            onValueChange = {name -> viewModel.onNameChange(name)},
            isError = {error -> viewModel.isFormError(error) },
            errorState = registerState.isFormError,
            leadIcon = R.drawable.profile_bold,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
        Spacer(Modifier.size(16.dp))
        TextFields(
            label = "Username",
            text = registerState.userName,
            enabled = false,
            onValueChange = {userName -> viewModel.onUserNameChange(userName)},
            isError = {error -> viewModel.isFormError(error) },
            errorState = registerState.isFormError,
            leadIcon = R.drawable.username,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
        Spacer(Modifier.size(16.dp))
        TextFields(
            label = "Email",
            text = registerState.email,
            enabled = false,
            onValueChange = {email -> viewModel.onEmailChange(email)},
            isError = {error -> viewModel.isFormError(error) },
            errorState = registerState.isFormError,
            leadIcon = R.drawable.profile_bold,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
        Spacer(Modifier.size(16.dp))
        TextFields(
            label = "Phone Number",
            text = registerState.phone,
            enabled = false,
            onValueChange = {phone -> viewModel.onPhoneChange(phone)},
            isError = {error -> viewModel.isFormError(error) },
            errorState = registerState.isFormError,
            leadIcon = R.drawable.username,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
        Spacer(Modifier.size(16.dp))
        PassField(
            label = "Password",
            text = registerState.password,
            enabled = false,
            errorState = registerState.isFormError,
            onValueChange = {password -> viewModel.onPasswordChange(password)},
            onViewClick = { viewModel.isVisible() },
            isError = {error -> viewModel.isFormError(error)},
            passwordVisibility = registerState.passwordVisibility,
            leadIcon = R.drawable.password,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            )
        )
        Spacer(Modifier.size(16.dp))
        Row{
            Checkbox(
                checked = registerState.checkBox,
                onCheckedChange = {viewModel.onCheckBoxChange()},
                colors = CheckboxDefaults.colors(
                    checkedColor = Color(0xFF27A74A),
                    checkmarkColor = Color.White,
                    uncheckedColor = Color(0xFFC5C5C5)
                )
            )
            Text(
                text = "saya telah menyetujui persyaratan dan persetujuan (privacy & policy) tandatangan.io"
            )
        }
        Spacer(Modifier.size(16.dp))
        Spacer(Modifier.weight(1f))
        Footer(
            label = "Register",
            onButtonClick = {
                if(!viewModel.isLastPageValid()) {
                    viewModel.isFormError(true)
                }else{
                    page.value = 1
                }
            }
        )
    }


}

@Composable
fun Footer(
    label: String,
    onButtonClick: () -> Unit
){
    Button(
        onClick = {
            onButtonClick()
        },
        content = {
            Text(text = label, color = Color.White)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(49.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF27A74A))
    )
    Spacer(Modifier.size(16.dp))
    Row{
        Text(
            text = "Already have an account? ",
            fontSize = 15.sp,
            color = Color.Black,
        )
        Text(
            text = "Sign In",
            fontSize = 15.sp,
            color = Color(0xFF27A74A),
            modifier = Modifier.clickable {  }
        )
    }
    Spacer(Modifier.size(16.dp))
    Text(
        text = "Forgot Password?",
        fontSize = 15.sp,
        color = Color.Black,
        modifier = Modifier.
        clickable {  }
    )
    Spacer(Modifier.size(16.dp))
    Text(
        text = "Privacy Policy",
        fontSize = 15.sp,
        color = Color.Black,
        modifier = Modifier.
        clickable {  }
    )
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterScreenPreview(){
    RegisterScreen()
}
