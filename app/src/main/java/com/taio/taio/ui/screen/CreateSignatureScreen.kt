package com.taio.taio.ui.screen

import androidx.compose.runtime.Composable
import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.taio.taio.R
import com.taio.taio.data.CreateState
import com.taio.taio.ui.theme.Green200
import com.taio.taio.viewmodel.CreateSignatureViewModel
import io.ak1.drawbox.DrawBox
import io.ak1.drawbox.DrawController
import io.ak1.drawbox.rememberDrawController

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
    Column{
        TtdGuide()
        TtdCanvas()
        TtdGuide2()
        ButtonFooter(
            label = "Simpan",
            onButtonClick = {
                page.value = 2
            }
        )
        ButtonFooterWhite(
            label = "Kembali",
            onButtonClick = {page.value = 2})
    }
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
@Composable
fun TtdGuide(){
    Column(modifier = Modifier
        .padding(16.dp)
    ) {
        Row{
            Image(painter = painterResource(R.drawable.generated_count),
                contentDescription = "logo Signature",
                modifier = Modifier.padding(5.dp))
            Text(text ="Live Signature",
                style = TextStyle(
                    fontSize=12.sp
                ),modifier = Modifier.padding(5.dp))
        }
        Spacer(modifier = Modifier.size(40.dp))
        Text(text= "Untuk buat tanda tangan gunakan kotak yang telah disediakan",
            style = TextStyle(
                fontSize=12.sp, textAlign = TextAlign.Center
            ))
    }

}

@Composable
fun TtdCanvas(){
    val undoVisibility = remember {mutableStateOf(false)}
    val redoVisibility = remember {mutableStateOf(false)}
    val drawController = rememberDrawController()

    Column(modifier = Modifier
        .padding(16.dp)
        .height(295.dp)
        .border(2.dp, Green200)) {
        drawController.setStrokeColor(Black)
        DrawBox(drawController = drawController,
            modifier = Modifier
                .weight(1f, true)
                .fillMaxSize()
        ) {
                undoCount, redoCount ->
            undoVisibility.value = undoCount !=0
            redoVisibility.value = redoCount !=0
        }
        ControlsBar(drawController = drawController,
            undoVisibility = undoVisibility,
            redoVisibility = redoVisibility )
    }
}


@Composable
fun RowScope.MenuItems(
    @DrawableRes resId: Int,
    desc: String,
    colorTint: androidx.compose.ui.graphics.Color,
    border: Boolean = false,
    onClick: () -> Unit
) {
    val modifier = Modifier.size(24.dp)
    IconButton(onClick = onClick, modifier = Modifier.weight(1f, true)) {
        Icon(
            painterResource(id = resId),
            contentDescription = desc,
            tint = colorTint,
            modifier = if(border) modifier.border(
                0.5.dp,
                White,
                shape = CircleShape
            ) else modifier
        )

    }
}


@Composable
fun ControlsBar(
    drawController: DrawController,
    undoVisibility: MutableState<Boolean>,
    redoVisibility: MutableState<Boolean>,
){
    Row(
        modifier = Modifier
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ){
        MenuItems(
            R.drawable.ic_baseline_undo_24,
            "undo",
            if (undoVisibility.value) MaterialTheme.colors.primary else MaterialTheme.colors.primaryVariant
        )   {
            if(undoVisibility.value) drawController.unDo()
        }
        MenuItems(
            resId = R.drawable.ic_baseline_redo_24,
            desc = "redo",
            colorTint =if (redoVisibility.value) MaterialTheme.colors.primary else MaterialTheme.colors.primaryVariant
        ) {
            if (redoVisibility.value) drawController.reDo()
        }
        MenuItems(
            R.drawable.ic_baseline_reset_24,
            "reset",
            if(redoVisibility.value || undoVisibility.value) MaterialTheme.colors.primary else MaterialTheme.colors.primaryVariant
        ){
            drawController.reset()
        }
    }
}


@Composable
fun TtdGuide2(){
    Text(text = "Hasil tanda tangan dapat dilihat pada signature, harap simpan terlebih dahulu tanda tangan yang telah dibuat",
        style = TextStyle(
            fontSize=12.sp,
            color = Gray,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier.padding(10.dp))
}


@Composable
fun ButtonFooterWhite(
    label: String,
    onButtonClick: () -> Unit
){
    Button(
        onClick = {
            onButtonClick()
        },
        content = {
            Text(text = label, color = Black)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(49.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = White)
    )
}

@Composable
fun ButtonGroupFooter(){
    Column(modifier=Modifier.padding(16.dp)) {
        ButtonFooter(label = "Simpan"){}
        Spacer(modifier = Modifier.size(10.dp))
        ButtonFooterWhite(label = "Kembali") {}
    }
}


@Composable
fun BuatTtdScreen(){
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
    ){
        Column{
            TtdGuide()
            TtdCanvas()
            TtdGuide2()
            Spacer(modifier = Modifier.size(80.dp))
            Spacer(Modifier.weight(1f))
            ButtonGroupFooter()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BuatTtdScreen()
}