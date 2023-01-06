package com.taio.taio.ui.screen

import android.widget.Button
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.taio.taio.R
import com.taio.taio.data.DataSource
import com.taio.taio.domain.model.SiganatureList
import com.taio.taio.domain.model.User
import com.taio.taio.domain.model.UserRequested
import com.taio.taio.ui.TandatanganioScreen
import com.taio.taio.ui.theme.*
import com.taio.taio.viewmodel.SearchViewModel

@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = viewModel()
){
    val searchTextState by searchViewModel.searchTextState
    val focusManager = LocalFocusManager.current
    var users = DataSource().loadUser()
    var docs = DataSource().loadRequested()
    var all = remember { mutableStateOf(true) }
    var user = remember { mutableStateOf(false) }
    var document = remember { mutableStateOf(false) }
    Column(modifier = Modifier.padding(16.dp)){
        Column {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(bottom = 16.dp)){
                IconButton(onClick = { navController.navigateUp()}) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
                TextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = searchTextState,
                    onValueChange = {
                        searchViewModel.updateSearchTextState(it)
                    },
                    textStyle = Typography.subtitle1,
                    singleLine = true,
                    trailingIcon = {
                        IconButton(onClick = {
                            if(searchTextState.isNotEmpty()){
                                searchViewModel.updateSearchTextState("")
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                null,
                                tint = Color.Black
                            )

                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Search
                    ),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            searchViewModel.searchUser(searchTextState)
                            focusManager.clearFocus()
                        }
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        textColor = Color.Black,
                        backgroundColor = Color.White,
                        focusedIndicatorColor = Color.Black
                    )
                )
            }

        }

        Column {
            Row{
                ButtonFilter(label = "Semua", border = if(!all.value) BorderStroke(1.dp, Green500) else null, textColor = if(all.value) Color.White else Green500, backgroundColor = if (all.value) Green500 else Color.White) {
                    all.value = true
                    user.value = false
                    document.value = false
                }
                Spacer(Modifier.width(10.dp))
                ButtonFilter(label = "Pengguna", border = if(!user.value) BorderStroke(1.dp, Green500) else null, textColor = if(user.value) Color.White else Green500, backgroundColor = if (user.value) Green500 else Color.White) {
                    all.value = false
                    user.value = true
                    document.value = false
                }
                Spacer(Modifier.width(10.dp))
                ButtonFilter(label = "Dokumen", border = if(!document.value) BorderStroke(1.dp, Green500) else null, textColor = if(document.value) Color.White else Green500, backgroundColor = if (document.value) Green500 else Color.White) {
                    all.value = false
                    user.value = false
                    document.value = true
                }
            }
        }
        Column{
            if (all.value){
                users = searchViewModel.searchUser(searchTextState)
                docs = searchViewModel.searchDocument(searchTextState)
            }
            if (user.value){
                users = searchViewModel.searchUser(searchTextState)
                docs = emptyList()
            }
            if (document.value){
                users = emptyList()
                docs = searchViewModel.searchDocument(searchTextState)
            }
            if (searchTextState != ""){
                users.forEach {
                    Users(user = it) {
                        navController.currentBackStackEntry?.savedStateHandle?.set(
                            key = "profile",
                            value = it
                        )
                        navController.navigate(TandatanganioScreen.OtherProfile.route)
                    }
                }
                docs.forEach{
                    Documents(document = it ) {

                    }
                }

            }
        }
    }

}

@Composable
fun ButtonFilter(
    label: String,
    border: BorderStroke?,
    backgroundColor: Color,
    textColor: Color,
    onClick: () -> Unit,
){
    OutlinedButton(
        onClick = {
            onClick()
        },
        content = {
            Text(
                text = label,
                style = Typography.body2.copy(fontSize = 10.sp),
                color = textColor
            )
        },
        border = border,
        modifier = Modifier
            .width(70.dp)
            .height(22.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor
        ),
        shape = RoundedCornerShape(20.dp),
        contentPadding = PaddingValues(0.dp)
    )
}

@Composable
fun Users(
    user: User,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Row(
        modifier = modifier
            .padding(PaddingValues(top = 15.dp))
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier = modifier
                .padding(end = 15.dp)
                .size(50.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            painter = painterResource(user.avatar),
            contentDescription = null
        )
        Column{
            Text(
                text = user.name,
                style = Typography.body1,
                color = Color.Black
            )
            Text(
                text = user.userName,
                style = Typography.body1,
                color = Gray700
            )
        }
    }
}

@Composable
fun Documents(
    document: UserRequested,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
){
    Row(
        modifier = modifier
            .padding(PaddingValues(top = 15.dp))
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painterResource(id = R.drawable.document),
            contentDescription = null,
            Modifier.size(50.dp),
            Blue500
        )
        Column(){
            Text(
                text = document.title,
                style = Typography.body1,
                color = Color.Black
            )
            Text(
                text = document.name,
                style = Typography.body1,
                color = Gray700
            )
            if (document.status == 2) {
                RequestedStatus(
                    text = "Ditandatangani",
                    MaterialTheme.colors.primary,
                    MaterialTheme.colors.onPrimary
                )
            }
            if (document.status == 0){
                RequestedStatus(
                    text = "Diajukan",
                    Yellow500,
                    MaterialTheme.colors.onSecondary
                )
            }
            if (document.status == 1){
                RequestedStatus(
                    text = "Ditolak",
                    Gray700,
                    MaterialTheme.colors.onPrimary
                )
            }
        }
    }
}

