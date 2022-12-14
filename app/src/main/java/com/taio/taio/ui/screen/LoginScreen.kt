package com.taio.taio.ui.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import androidx.navigation.compose.rememberNavController
import com.google.gson.Gson
import com.taio.taio.R
import com.taio.taio.data.LoginState
import com.taio.taio.data.model.ErrorResponse
import com.taio.taio.data.model.Users
import com.taio.taio.data.utils.Result
import com.taio.taio.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint


@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel()
) {
    val loginState: LoginState = viewModel.loginState.collectAsState().value
    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    LaunchedEffect(viewModel, context){
        viewModel.authResults.collect{ result ->
            when(result){
                is Result.Authorized -> {
                    val gson = Gson()
                    val json = gson.toJson(result.data)
                    Log.w("LoginScreen", json)
                }

                is Result.Unathorized -> {
                    Toast.makeText(
                        context,
                        "You're not authorized",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is Result.ApiError -> {
                    val apiResponse: ErrorResponse? = result.data as ErrorResponse?
                    viewModel.validate(apiResponse)

                    Toast.makeText(
                        context,
                        apiResponse?.message?:"An error occured",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }




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
            text = loginState.email,
            onValueChange = {email -> viewModel.updateEmail(email) },
            errorState = loginState.isFormError,
            isError = {error -> viewModel.isFormError(error) },
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
            text = loginState.password,
            onValueChange = {password -> viewModel.updatePassword(password)},
            onViewClick = { viewModel.isVisible() },
            errorState = loginState.isFormError,
            isError = {error -> viewModel.isFormError(error)},
            passwordVisibility = loginState.passwordVisibility,
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
        if (loginState.isFormError) {
            Row{
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
                if(!viewModel.isFormValid()) {
                    viewModel.isFormError(true)
                }else{
                    Toast.makeText(
                        context,
                        "Logged in successfully",
                        Toast.LENGTH_SHORT
                    ).show()
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
        Row{
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
    text: String,
    enabled: Boolean = true,
    onValueChange: (String) -> Unit,
    isError: (Boolean) -> Unit,
    errorState: Boolean,
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
        modifier = Modifier
            .fillMaxWidth(),
        value = text,
        enabled = enabled,
        onValueChange = {
            if (errorState) {
                isError(false)
            }
            onValueChange(it)
        },
        leadingIcon = {
            Icon(
                painterResource(leadIcon),
                contentDescription = label,
                tint = if (errorState) Color(0xFFDC0404) else Color(0xFFC5C5C5),
                modifier = Modifier.size(24.dp)
            )
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = if (errorState) Color(0xFFDC0404) else Color(0xFFC5C5C5),
            focusedBorderColor = Color(0xFF27A74A)
        )

    )
}

@Composable
fun PassField(
    label: String,
    text: String,
    enabled: Boolean = true,
    errorState: Boolean,
    onValueChange: (String) -> Unit,
    onViewClick: () -> Unit,
    isError: (Boolean) -> Unit,
    passwordVisibility: Boolean,
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
        value = text,
        enabled = enabled,
        onValueChange = {
            if (errorState) {
                isError(false)
            }
            onValueChange(it)
        },
        modifier = Modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = if (errorState) Color(0xFFDC0404) else Color(0xFFC5C5C5),
            focusedBorderColor = Color(0xFF27A74A)
        ),
        leadingIcon = {
            Icon(
                painterResource(leadIcon),
                contentDescription = "Password",
                tint = if (errorState) Color(0xFFDC0404) else Color(0xFFC5C5C5),
                modifier = Modifier.size(24.dp)
            )
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        trailingIcon = {
            IconButton(onClick = {
                onViewClick()
            }) {
                Icon(
                    painter = painterResource(if (passwordVisibility) R.drawable.eye_off else R.drawable.eye_on),
                    contentDescription = "visibility",
                    tint = Color(0xFF27A74A),
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        visualTransformation = if (passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None
    )
}

@Preview
@Composable
fun LoginPreview(){
    val navController = rememberNavController()
    LoginScreen(navController)
}
