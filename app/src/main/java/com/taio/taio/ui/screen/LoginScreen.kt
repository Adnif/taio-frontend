package com.taio.taio.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.taio.taio.R

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    val email = remember { mutableStateOf(TextFieldValue()) }
    val errorState = remember { mutableStateOf(false) }
    val password = remember { mutableStateOf(TextFieldValue()) }
    val focusManager = LocalFocusManager.current
    val passwordVisibility = remember { mutableStateOf(true) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
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
            label = "Email",
            text = email,
            errorState = errorState,
            leadIcon = R.drawable.profile,
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
            label = "Password",
            text = password,
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

        Spacer(Modifier.size(16.dp))
        if (errorState.value) {
            Row(){
                Spacer(Modifier.weight(1f))
                Text(
                    text = "email atau password salah",
                    color = Color(0xFFDC0404),
                    fontSize = 15.sp,
                    modifier = Modifier.padding(PaddingValues(end = 10.dp))
                )
                Icon(
                    painterResource(R.drawable.wrong),
                    contentDescription = "Wrong",
                    tint = Color(0xFFDC0404),
                    modifier = Modifier.size(24.dp)
                )
            }
            Spacer(Modifier.size(16.dp))
        }
        Button(
            onClick = {
                when {
                    email.value.text.isEmpty() -> {
                        errorState.value = true
                    }
                    password.value.text.isEmpty() -> {
                        errorState.value = true
                    }
                    else -> {
                        errorState.value = false
                        Toast.makeText(
                            context,
                            "Logged in successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            },
            content = {
                Text(text = "Login", color = Color.White)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(49.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF27A74A))
        )
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom
    ){
        Row(

        ) {
            Spacer(Modifier.weight(1f))
            Text(
                text = "Doesn't have an account?",
                fontSize = 15.sp,
                color = Color.Black,
            )
            Text(
                text = "Sign Up",
                fontSize = 15.sp,
                color = Color(0xFF27A74A),
                modifier = Modifier.clickable {  }
            )
        }
    }


}

@Composable
fun TextFields(
    label: String,
    text: MutableState<TextFieldValue>,
    errorState: MutableState<Boolean>,
    leadIcon: Int,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
){

    Text(
        text = label,
        fontSize = 15.sp,
        color = Color.Black,
        modifier = Modifier.padding(bottom = 10.dp)
    )
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text.value,
        onValueChange = {
            if (errorState.value) {
                errorState.value = false
            }
            text.value = it
        },
        leadingIcon = {
            Icon(
                painterResource(leadIcon),
                contentDescription = label,
                tint = if (errorState.value) Color(0xFFDC0404) else Color(0xFFC5C5C5),
                modifier = Modifier.size(24.dp)
            )
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = if (errorState.value) Color(0xFFDC0404) else Color(0xFFC5C5C5),
            focusedBorderColor = Color(0xFF27A74A)
        )

    )
}

@Composable
fun PassField(
    label: String,
    text: MutableState<TextFieldValue>,
    errorState: MutableState<Boolean>,
    passwordVisibility: MutableState<Boolean>,
    leadIcon: Int,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
){
    Text(
        text = label,
        fontSize = 15.sp,
        color = Color(0xFF000000),
        modifier = Modifier.padding(bottom = 10.dp)
    )
    OutlinedTextField(
        value = text.value,
        onValueChange = {
            if (errorState.value) {
                errorState.value = false
            }
            text.value = it
        },
        isError = errorState.value,
        modifier = Modifier.fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = if (errorState.value) Color(0xFFDC0404) else Color(0xFFC5C5C5),
            focusedBorderColor = Color(0xFF27A74A)
        ),
        leadingIcon = {
            Icon(
                painterResource(leadIcon),
                contentDescription = "Password",
                tint = if (errorState.value) Color(0xFFDC0404) else Color(0xFFC5C5C5)
            )
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        trailingIcon = {
            IconButton(onClick = {
                passwordVisibility.value = !passwordVisibility.value
            }) {
                Icon(
                    painter = painterResource(if (passwordVisibility.value) R.drawable.eye_on else R.drawable.eye_off),
                    contentDescription = "visibility",
                    tint = Color(0xFF27A74A)
                )
            }
        },
        visualTransformation = if (passwordVisibility.value) PasswordVisualTransformation() else VisualTransformation.None
    )
}

@Preview
@Composable
fun LoginPreview(){
    val navController = rememberNavController()
    LoginScreen(navController)
}
