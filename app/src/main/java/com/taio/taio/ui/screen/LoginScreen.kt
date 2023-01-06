package com.taio.taio.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.taio.taio.R
import com.taio.taio.data.LoginState
import com.taio.taio.ui.TandatanganioScreen
import com.taio.taio.ui.theme.*
import com.taio.taio.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel()
) {
    val loginState: LoginState = viewModel.loginState.collectAsState().value
    val focusManager = LocalFocusManager.current
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .heightIn(min = maxHeight)
                .padding(16.dp),
        ) {
            Spacer(Modifier.weight(1f))
            Column(
            ) {
                Text(
                    text = stringResource(id = R.string.header_login),
                    style = Typography.h2,
                    color = Color.Black
                )
                Image(
                    painter = painterResource(id = R.drawable.logo_taio),
                    contentDescription = stringResource(R.string.logo_taio),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.size(16.dp))
                TextFields(
                    label = stringResource(R.string.email_label),
                    text = loginState.email,
                    onValueChange = { email -> viewModel.updateEmail(email) },
                    errorState = loginState.isFormError,
                    isError = { error -> viewModel.isFormError(error) },
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
                    label = stringResource(R.string.password_label),
                    text = loginState.password,
                    onValueChange = { password -> viewModel.updatePassword(password)},
                    onViewClick = { viewModel.isVisible() },
                    errorState = loginState.isFormError,
                    isError = { error -> viewModel.isFormError(error)},
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
                            text = stringResource(R.string.login_form_error),
                            color = Red500,
                            style = Typography.subtitle1,
                            modifier = Modifier.padding(PaddingValues(end = 10.dp))
                        )
                        Icon(
                            painterResource(R.drawable.wrong),
                            contentDescription = stringResource(R.string.content_description_wrong),
                            tint = Red500,
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
                            navController.popBackStack()
                            navController.navigate(TandatanganioScreen.Home.route)
                        }

                    },
                    content = {
                        Text(
                            text = stringResource(R.string.login_label),
                            style = Typography.button,
                            color = Color.White
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(49.dp),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Green500)
                )
                Spacer(Modifier.size(16.dp))
                Text(
                    text = stringResource(R.string.forgot_password),
                    style = Typography.subtitle1,
                    color = Color.Black,
                    modifier = Modifier.
                    clickable {  }
                )
                Spacer(Modifier.size(16.dp))
                Text(
                    text = stringResource(id = R.string.privacy_policy),
                    style = Typography.subtitle1,
                    color = Color.Black,
                    modifier = Modifier.
                    clickable {  }
                )


            }
            Spacer(Modifier.weight(1f))
            Column(
            ){
                Row{
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = stringResource(id = R.string.no_account),
                        style = Typography.subtitle1,
                        color = Color.Black,
                        modifier = Modifier.padding(end = 5.dp)
                    )
                    Text(
                        text = stringResource(id = R.string.sign_up),
                        style = Typography.subtitle1,
                        color = Green500,
                        modifier = Modifier.clickable {
                            navController.popBackStack()
                            navController.navigate(TandatanganioScreen.Register.route)
                        }
                    )
                }
            }
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
        style = Typography.subtitle1,
        color = Color.Black,
        modifier = Modifier.padding(bottom = 10.dp)
    )
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(49.dp)
            .padding(0.dp),
        value = text,
        textStyle = Typography.body2,
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
                tint = if (errorState) Red500 else Gray300,
                modifier = Modifier.size(24.dp)
            )
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = if (errorState) Red500 else Gray300,
            focusedBorderColor = Green500,
            textColor = Gray700
        ),
        singleLine = true

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
        style = Typography.subtitle1,
        color = Color.Black,
        modifier = Modifier.padding(bottom = 10.dp)
    )
    OutlinedTextField(
        value = text,
        enabled = enabled,
        textStyle = Typography.body2,
        onValueChange = {
            if (errorState) {
                isError(false)
            }
            onValueChange(it)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(49.dp)
            .padding(0.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = if (errorState) Red500 else Gray300,
            focusedBorderColor = Green500,
            textColor = Gray700
        ),
        leadingIcon = {
            Icon(
                painterResource(leadIcon),
                contentDescription = stringResource(R.string.content_description_password),
                tint = if (errorState) Red500 else Gray300,
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
                    contentDescription = stringResource(R.string.content_description_visibility),
                    tint = Green500,
                    modifier = Modifier.size(24.dp)
                )
            }
        },
        visualTransformation = if (passwordVisibility) PasswordVisualTransformation() else VisualTransformation.None,
        singleLine = true
    )
}

@Preview(showSystemUi = true)
@Composable
fun LoginPreview(){
    val navController = rememberNavController()
    LoginScreen(navController)
}
