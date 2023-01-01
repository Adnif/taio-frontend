package com.taio.taio.ui.screen

import androidx.compose.runtime.Composable
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.taio.taio.data.CreateState
import com.taio.taio.viewmodel.CreateSignatureViewModel

@Composable
fun TestCreateScreen(navController: NavController){
    CreateSignatureScreen()
}

@Composable
fun CreateSignatureScreen(viewModel: CreateSignatureViewModel = viewModel()){
    val createState = viewModel.createState.collectAsState().value
    val page = remember {
        mutableStateOf(1)
    }
    val focusManager = LocalFocusManager.current
    BackHandler(enabled = true) {
        if(page.value == 1) {
            page.value = 1
        }else{
            page.value = page.value - 1
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxHeight(),
        topBar = {
            TopAppBar() {
                Text(
                    text = "Buat Tanda Tangan",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    ) {
        when(page.value){
            1 -> PageOne(
                viewModel = viewModel,
                createState = createState,
                page = page,
                focusManager = focusManager,
            )
            2 -> PageTwo(
                viewModel = viewModel,
                createState = createState,
                page = page,
                focusManager = focusManager,
            )
            else -> PageThree(
                viewModel = viewModel,
                createState = createState,
                page = page,
                focusManager = focusManager,
            )
        }
    }
}


@Composable
fun PageOne(
    viewModel: CreateSignatureViewModel,
    createState: CreateState,
    page: MutableState<Int>,
    focusManager: FocusManager
){
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
            .padding(top = 10.dp),
    ) {

        Spacer(modifier = Modifier.size(16.dp))

        SmallTextField(
            label = "Judul/Nama Dokumen*",
            placeholder = "Masukkan Judul/Nama Dokumen",
            text = createState.documentName,
            onValueChange = {documentName -> viewModel.onDocName(documentName)},
            isError = {error -> viewModel.isFormError(error)},
            errorState = createState.isFormError,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
        Spacer(modifier = Modifier.size(16.dp))

        SmallTextField(
            label = "Tanggal Dokumen*",
            placeholder = "Tanggal(yyyy/mm/dd)",
            text = createState.documentDate,
            onValueChange = {documentDate -> viewModel.onDocDate(documentDate)},
            isError = {error -> viewModel.isFormError(error)},
            errorState = createState.isFormError,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
        Spacer(modifier = Modifier.size(16.dp))

        SmallTextField(
            label = "Nomor Dokumen (Optional)",
            placeholder = "Masukkan Nomor Dokumen",
            text = createState.documentNumber,
            onValueChange = {documentNumber -> viewModel.onDocNumber(documentNumber)},
            isError = {error -> viewModel.isFormError(error)},
            errorState = createState.isFormError,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
        Spacer(modifier = Modifier.size(16.dp))

        LargeTextField(
            label = "Deskripsi Dokumen*",
            placeholder = "Uraikan keterangan dokumen yang ditandatangani...",
            text = createState.documentDescription,
            onValueChange = {documentDescription -> viewModel.onDocDesc(documentDescription)},
            isError = {error -> viewModel.isFormError(error)},
            errorState = createState.isFormError,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                }
            )
        )
        Spacer(Modifier.size(80.dp))
        Spacer(Modifier.weight(1f))
        ButtonFooter(
            label = "Next",
            onButtonClick = {
                if(!viewModel.isPageOneValid()) {
                    viewModel.isFormError(true)
                }else{
                    page.value = 2
                }
            }
        )
        Spacer(Modifier.size(80.dp))
    }
}

@Composable
fun PageTwo(
    viewModel: CreateSignatureViewModel,
    createState: CreateState,
    page: MutableState<Int>,
    focusManager: FocusManager
){
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .padding(16.dp)
        .padding(top = 10.dp)) {

    }
}

@Composable
fun PageThree(
    viewModel: CreateSignatureViewModel,
    createState: CreateState,
    page: MutableState<Int>,
    focusManager: FocusManager
){

}

@Composable
fun ButtonFooter(
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
}

/*Fun Untuk SmallTextField*/
@Composable
fun SmallTextField(
    label: String,
    text: String,
    enabled: Boolean = true,
    onValueChange: (String) -> Unit,
    isError: (Boolean) -> Unit,
    errorState: Boolean,
    placeholder: String,
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
            .fillMaxSize()
            .height(50.dp),
        value = text,
        enabled = enabled,
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 14.sp
            )
        },
        onValueChange = {
            if (errorState) {
                isError(false)
            }
            onValueChange(it)
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = if (errorState) Color(0xFFDC0404) else Color(0xFFC5C5C5),
            focusedBorderColor = Color(0xFF27A74A)
        )

    )
}

/*Fun Untuk LargeTextField*/
@Composable
fun LargeTextField(
    label: String,
    text: String,
    enabled: Boolean = true,
    onValueChange: (String) -> Unit,
    isError: (Boolean) -> Unit,
    errorState: Boolean,
    placeholder: String,
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
            .fillMaxSize()
            .height(190.dp),
        value = text,
        enabled = enabled,
        placeholder = {
            Text(
                text = placeholder,
                fontSize = 14.sp
            )
        },
        onValueChange = {
            if (errorState) {
                isError(false)
            }
            onValueChange(it)
        },
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            unfocusedBorderColor = if (errorState) Color(0xFFDC0404) else Color(0xFFC5C5C5),
            focusedBorderColor = Color(0xFF27A74A)
        )

    )
}