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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.taio.taio.R
import com.taio.taio.data.RegisterState
import com.taio.taio.ui.TandatanganioScreen
import com.taio.taio.ui.theme.Gray300
import com.taio.taio.ui.theme.Green500
import com.taio.taio.ui.theme.Green700
import com.taio.taio.ui.theme.Typography
import com.taio.taio.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(
    navController: NavController,
    viewModel: RegisterViewModel = viewModel()
){
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
            navController = navController,
        )
        2 -> SecondPage(
            viewModel = viewModel,
            registerState = registerState,
            page = page,
            focusManager = focusManager,
            navController = navController,
        )
        3 -> ThirdPage(
            viewModel = viewModel,
            registerState = registerState,
            page = page,
            focusManager = focusManager,
            navController = navController,
        )
        else -> LastPage(
            viewModel = viewModel,
            registerState = registerState,
            navController = navController,
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
    navController: NavController,
){
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .heightIn(min = maxHeight)
                .padding(16.dp),
        ){
            Column(
            ){
                Text(
                    text = stringResource(R.string.header_login),
                    style = Typography.h2,
                    color = Color.Black
                )
                Image(
                    painter = painterResource(id = R.drawable.logo_taio),
                    contentDescription = stringResource(id = R.string.logo_taio),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.size(16.dp))
                TextFields(
                    label = stringResource(R.string.name_label),
                    text = registerState.name,
                    onValueChange = { name -> viewModel.onNameChange(name)},
                    isError = { error -> viewModel.isFormError(error) },
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
                    label = stringResource(R.string.username_label),
                    text = registerState.userName,
                    onValueChange = { userName -> viewModel.onUserNameChange(userName)},
                    isError = { error -> viewModel.isFormError(error) },
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
            }
            Spacer(Modifier.weight(1f))
            Footer(
                label = stringResource(id = R.string.next_button),
                navController = navController,
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



}

@Composable
fun SecondPage(
    viewModel: RegisterViewModel,
    registerState: RegisterState,
    page: MutableState<Int>,
    focusManager: FocusManager,
    navController: NavController,
){
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .heightIn(min = maxHeight)
                .padding(16.dp),
        ) {
            TextFields(
                label = stringResource(id = R.string.email_label),
                text = registerState.email,
                onValueChange = { email -> viewModel.onEmailChange(email) },
                isError = { error -> viewModel.isFormError(error) },
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
                label = stringResource(id = R.string.phone_number_label),
                text = registerState.phone,
                onValueChange = { phone -> viewModel.onPhoneChange(phone) },
                isError = { error -> viewModel.isFormError(error) },
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
                label = stringResource(R.string.next_button),
                navController = navController,
                onButtonClick = {
                    if (!viewModel.isPageTwoValid()) {
                        viewModel.isFormError(true)
                    } else {
                        page.value = 3
                    }
                }
            )
        }
    }

}

@Composable
fun ThirdPage(
    viewModel: RegisterViewModel,
    registerState: RegisterState,
    page: MutableState<Int>,
    focusManager: FocusManager,
    navController: NavController,
){
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .heightIn(min = maxHeight)
                .padding(16.dp),
        ) {
            PassField(
                label = stringResource(id = R.string.password_label),
                text = registerState.password,
                errorState = registerState.isFormError,
                onValueChange = { password -> viewModel.onPasswordChange(password) },
                onViewClick = { viewModel.isVisible() },
                isError = { error -> viewModel.isFormError(error) },
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
                label = stringResource(R.string.password_confirm_label),
                text = registerState.passConfirm,
                errorState = registerState.isFormError,
                onValueChange = { password -> viewModel.onPassConfirmChange(password) },
                onViewClick = { viewModel.isVisible() },
                isError = { error -> viewModel.isFormError(error) },
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
                label = stringResource(R.string.next_button),
                navController = navController,
                onButtonClick = {

                    if (!viewModel.isPageThreeValid() || !viewModel.isPasswordMatch() || !viewModel.isPassMetRequirement()) {
                        viewModel.isFormError(true)
                    } else {
                        page.value = 4
                    }
                }
            )
        }
    }

}

@Composable
fun LastPage(
    viewModel: RegisterViewModel,
    registerState: RegisterState,
    navController: NavController,
    focusManager: FocusManager,
){
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .heightIn(min = maxHeight)
                .padding(16.dp),
        ) {
            TextFields(
                label = stringResource(id = R.string.name_label),
                text = registerState.name,
                enabled = false,
                onValueChange = { name -> viewModel.onNameChange(name) },
                isError = { error -> viewModel.isFormError(error) },
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
                label = stringResource(R.string.username_label),
                text = registerState.userName,
                enabled = false,
                onValueChange = { userName -> viewModel.onUserNameChange(userName) },
                isError = { error -> viewModel.isFormError(error) },
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
                label = stringResource(id = R.string.email_label),
                text = registerState.email,
                enabled = false,
                onValueChange = { email -> viewModel.onEmailChange(email) },
                isError = { error -> viewModel.isFormError(error) },
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
                label = stringResource(id = R.string.phone_number_label),
                text = registerState.phone,
                enabled = false,
                onValueChange = { phone -> viewModel.onPhoneChange(phone) },
                isError = { error -> viewModel.isFormError(error) },
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
                label = stringResource(id = R.string.password_label),
                text = registerState.password,
                enabled = false,
                errorState = registerState.isFormError,
                onValueChange = { password -> viewModel.onPasswordChange(password) },
                onViewClick = { viewModel.isVisible() },
                isError = { error -> viewModel.isFormError(error) },
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
            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(
                    checked = registerState.checkBox,
                    onCheckedChange = { viewModel.onCheckBoxChange() },
                    colors = CheckboxDefaults.colors(
                        checkedColor = Green700,
                        checkmarkColor = Color.White,
                        uncheckedColor = Gray300
                    )
                )
                Text(
                    text = stringResource(R.string.check_privacy_policy),
                    style = Typography.overline
                )
            }
            Spacer(Modifier.size(16.dp))
            Spacer(Modifier.weight(1f))
            Footer(
                label = stringResource(id = R.string.sign_up),
                navController = navController,
                onButtonClick = {
                    if (!viewModel.isLastPageValid()) {
                        viewModel.isFormError(true)
                    } else {
                        navController.popBackStack()
                        navController.navigate(TandatanganioScreen.EmailCheck.route)
                    }
                }
            )
        }
    }


}

@Composable
fun Footer(
    label: String,
    onButtonClick: () -> Unit,
    navController: NavController,
){
    Column(
    ){
        Button(
            onClick = {
                onButtonClick()
            },
            content = {
                Text(
                    text = label,
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
        Row{
            Text(
                text = stringResource(R.string.have_account),
                style = Typography.subtitle1,
                color = Color.Black,
                modifier = Modifier.padding(end = 10.dp)
            )
            Text(
                text = stringResource(R.string.login_label),
                style = Typography.subtitle1,
                color = Green500,
                modifier = Modifier.clickable {
                    navController.popBackStack()
                    navController.navigate(TandatanganioScreen.Login.route)
                }
            )
        }
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
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun RegisterScreenPreview(){
    val navController = rememberNavController()
    RegisterScreen(navController)
}
