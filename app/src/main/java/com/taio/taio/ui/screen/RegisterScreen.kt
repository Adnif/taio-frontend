package com.taio.taio.ui.screen

import androidx.compose.foundation.Image
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.taio.taio.R

@Composable
fun RegisterScreen(){
    val errorState = remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    FirstPage(
        errorState = errorState,
        focusManager = focusManager
    )
}


@Composable
fun FirstPage(
    errorState: MutableState<Boolean>,
    focusManager: FocusManager,
){
    val name = remember { mutableStateOf(TextFieldValue()) }
    val userName = remember { mutableStateOf(TextFieldValue()) }
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ){
        Button(
            onClick = {
                when {
                    userName.value.text.isEmpty() -> {
                        errorState.value = true
                    }
                    else -> {
                        errorState.value = false
                    }
                }

            },
            content = {
                Text(text = "Next", color = Color.White)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(49.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF27A74A))
        )
    }
}

@Preview
@Composable
fun RegisterScreenPreview(){
    RegisterScreen()
}
