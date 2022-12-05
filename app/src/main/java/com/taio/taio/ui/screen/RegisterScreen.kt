package com.taio.taio.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.taio.taio.R

@Composable
fun RegisterScreen(){
    val page = remember { mutableStateOf(1) }
    val errorState = remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val name = remember { mutableStateOf(TextFieldValue()) }
    val userName = remember { mutableStateOf(TextFieldValue()) }
    val email = remember { mutableStateOf(TextFieldValue()) }
    val phone = remember { mutableStateOf(TextFieldValue()) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val passConfirm = remember { mutableStateOf(TextFieldValue()) }
    val passwordVisibility = remember { mutableStateOf(true) }
    when(page.value){
        1 -> FirstPage(
            name = name,
            userName = userName,
            page = page,
            errorState = errorState,
            focusManager = focusManager,
        )
        2 -> SecondPage(
            email = email,
            phone = phone,
            page = page,
            errorState = errorState,
            focusManager = focusManager
        )
        3 -> ThirdPage(
            password = password,
            passConfirm = passConfirm,
            page = page,
            errorState = errorState,
            focusManager = focusManager,
            passwordVisibility = passwordVisibility
        )
        else -> LastPage(
            name = name,
            userName = userName,
            email = email,
            phone = phone,
            password = password,
            passConfirm = passConfirm,
            page = page,
            errorState = errorState,
            focusManager = focusManager,
            passwordVisibility = passwordVisibility
        )

    }

}


@Composable
fun FirstPage(
    name: MutableState<TextFieldValue>,
    userName: MutableState<TextFieldValue>,
    page: MutableState<Int>,
    errorState: MutableState<Boolean>,
    focusManager: FocusManager,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
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
            text = name,
            errorState = errorState,
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
            text = userName,
            errorState = errorState,
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
    }
    Footer(
        label = "Next",
        onButtonClick = {
            when {
                userName.value.text.isEmpty() -> {
                    errorState.value = true
                }
                else -> {
                    errorState.value = false
                    page.value = 2
                }
            }
        }
    )

}

@Composable
fun SecondPage(
    email: MutableState<TextFieldValue>,
    phone: MutableState<TextFieldValue>,
    page: MutableState<Int>,
    errorState: MutableState<Boolean>,
    focusManager: FocusManager,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ){
        TextFields(
            label = "Email",
            text = email,
            errorState = errorState,
            leadIcon = R.drawable.email,
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
            text = phone,
            errorState = errorState,
            leadIcon = R.drawable.phone,
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
    }
    Footer(
        label = "Next",
        onButtonClick = {
            page.value = 3
        }
    )

}

@Composable
fun ThirdPage(
    password: MutableState<TextFieldValue>,
    passConfirm: MutableState<TextFieldValue>,
    page: MutableState<Int>,
    errorState: MutableState<Boolean>,
    focusManager: FocusManager,
    passwordVisibility: MutableState<Boolean>
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ){
        PassField(
            label = "Password",
            text = password,
            errorState = errorState,
            passwordVisibility = passwordVisibility,
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
            text = passConfirm,
            errorState = errorState,
            passwordVisibility = passwordVisibility,
            leadIcon = R.drawable.password,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
    }
    Footer(
        label = "Next",
        onButtonClick = {
            when {
                !password.value.text.equals(passConfirm.value.text) -> {
                    errorState.value = true
                }
                else -> {
                    errorState.value = false
                    page.value = 4
                }
            }
        }
    )

}

@Composable
fun LastPage(
    name: MutableState<TextFieldValue>,
    userName: MutableState<TextFieldValue>,
    email: MutableState<TextFieldValue>,
    phone: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>,
    passConfirm: MutableState<TextFieldValue>,
    page: MutableState<Int>,
    errorState: MutableState<Boolean>,
    focusManager: FocusManager,
    passwordVisibility: MutableState<Boolean>
){
    Column(
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ){
        TextFields(
            label = "Nama Lengkap",
            text = name,
            errorState = errorState,
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
        TextFields(
            label = "Username",
            text = userName,
            errorState = errorState,
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
        TextFields(
            label = "Email",
            text = email,
            errorState = errorState,
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
        TextFields(
            label = "Phone Number",
            text = phone,
            errorState = errorState,
            leadIcon = R.drawable.phone,
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
        PassField(
            label = "Password",
            text = password,
            errorState = errorState,
            passwordVisibility = passwordVisibility,
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
        PassField(
            label = "Password Confirm",
            text = passConfirm,
            errorState = errorState,
            passwordVisibility = passwordVisibility,
            leadIcon = R.drawable.password,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
    }
    Footer(
        label = "Register",
        onButtonClick = {
            errorState.value = false
        }
    )

}

@Composable
fun Footer(
    label: String,
    onButtonClick: () -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
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
        Row(

        ) {
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
}

@Preview
@Composable
fun RegisterScreenPreview(){
    RegisterScreen()
}
