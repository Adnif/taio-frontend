package com.taio.taio.ui.theme.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.taio.taio.R
import com.taio.taio.ui.theme.fonts
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext


@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    name: String,
){
    Column() {
        val mContext = LocalContext.current
        Row(modifier = modifier
            .padding(PaddingValues(top = 65.dp, start = 24.dp))
        ) {
            Image(
                modifier = modifier
                    .size(74.dp)
                    .clip(RoundedCornerShape(500.dp)),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = null
            )
            Column(modifier = modifier
                .padding(PaddingValues(start = 48.dp, top = 13.dp))
                .width(179.dp)
                .height(48.dp)
            ){
                Text(
                    text = "Halo, Selamat Datang $name",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W600,
                    fontFamily = fonts,
                    color = Color(0xFF1D211D),
                    lineHeight = 24.sp
                )
            }
        }
        Row(
            modifier = modifier
                .padding(PaddingValues(top = 43.dp))
                .align(Alignment.CenterHorizontally)
        ){

            var searchText by remember { mutableStateOf("") }
            SearchBar(
                text = searchText,
                onTextChange = {searchText = it},
                onSearchClicked = {Toast.makeText(
                    mContext, searchText, Toast.LENGTH_LONG).show()}
            )
        }
        Row(
            modifier = modifier
                .padding(PaddingValues(top = 30.dp))
        ){
            Column(
                modifier = modifier
                    .padding(PaddingValues(start = 26.dp,end = 17.dp))
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send Icon",
                    tint = Color(0xFF28A745),
                )
            }
            Column(
                modifier = modifier
                    .padding(PaddingValues(end = 127.dp))
            ) {
                Text(
                    text = "Minta Cepat",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500,
                    fontFamily = fonts,
                    color = Color(0xFF000000),
                    lineHeight = 21.sp
                )
            }
            Column(
                modifier = modifier
                    .padding(PaddingValues(end = 18.dp))
            ) {
                Text(
                    modifier = Modifier.clickable { Toast.makeText(
                        mContext, "Minta Cepat Selengkapnya", Toast.LENGTH_LONG).show() },
                    textAlign = TextAlign.End,
                    text = "Selengkapnya",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = fonts,
                    color = Color(0xFF615D5D),
                    lineHeight = 18.sp,
                )
            }

        }

        AskFast(avatar = R.drawable.avatar, name = "Ahmad Bani Faqih")
        AskFast(avatar = R.drawable.avatar, name = "Ahmad Luhur Pakerti")
        AskFast(avatar = R.drawable.avatar, name = "Chiko Tridipa")

        Row(
            modifier = modifier
                .padding(PaddingValues(top = 31.dp))
        ){
            Column(
                modifier = modifier
                    .padding(PaddingValues(start = 26.dp,end = 17.dp))
            ) {
                Icon(
                    imageVector = Icons.Default.AccountBox,
                    contentDescription = null,
                    tint = Color(0xFF28A745),

                )
            }
            Column(
                modifier = modifier
                    .padding(PaddingValues(end = 131.dp))
            ) {
                Text(
                    text = "Permintaan",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.W500,
                    fontFamily = fonts,
                    color = Color(0xFF000000),
                    lineHeight = 21.sp
                )
            }
            Column(
                modifier = modifier
                    .padding(PaddingValues(end = 18.dp))
            ) {
                Text(
                    modifier = Modifier.clickable { Toast.makeText(
                        mContext, "Permintaan Selengkapnya", Toast.LENGTH_LONG).show() },
                    textAlign = TextAlign.End,
                    text = "Selengkapnya",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    fontFamily = fonts,
                    color = Color(0xFF615D5D),
                    lineHeight = 18.sp,
                )
            }

        }

    }
}

@Composable
fun SearchBar(
    text: String,
    onTextChange: (String) -> Unit,
    onSearchClicked: (String) -> Unit
){
   OutlinedTextField(
       modifier = Modifier
           .width(342.dp)
           .height(48.dp)
           .clip(RoundedCornerShape(5.dp)),
       value = text,
       onValueChange = onTextChange,
       placeholder = {
           Text(
               text = "Mau minta tandatangan siapa ?",
               color = Color(0xFF615D5D),
               fontSize = 12.sp,
               fontWeight = FontWeight.W400,
               fontFamily = fonts,
               lineHeight = 18.sp
           )
       },
       textStyle = TextStyle(
           color = Color(0xFF615D5D),
           fontSize = 12.sp,
           fontWeight = FontWeight.W400,
           fontFamily = fonts,
           lineHeight = 18.sp
       ),
       colors = TextFieldDefaults.outlinedTextFieldColors(
           focusedBorderColor = Color(0xFF28A745)
       ),
       singleLine = true,
       leadingIcon = {
           IconButton(
               onClick = {}
           ) {
               Icon(
                   imageVector = Icons.Default.Search,
                   contentDescription = "Search Icon",
                   tint = Color(0xFF615D5D)
               )
           }
       },
       trailingIcon = {
           IconButton(
               onClick = {
                   onTextChange("")
               }
           ) {
               Icon(
                   imageVector = Icons.Default.Close,
                   contentDescription = "Close Icon",
                   tint = Color(0xFF615D5D)
               )
           }
       },
       keyboardOptions = KeyboardOptions(
           imeAction = ImeAction.Search
       ),
       keyboardActions = KeyboardActions(
           onSearch = { onSearchClicked(text) }
       )
   )
}

@Composable
fun AskFast(
    avatar: Int,
    name : String,
    modifier: Modifier = Modifier
){
    val mContext = LocalContext.current
    Row(
        modifier = modifier
            .clickable { Toast.makeText(
                mContext, name, Toast.LENGTH_LONG).show() }
            .padding(PaddingValues(start = 24.dp, top = 17.dp))
    ) {
        Image(
            modifier = modifier
                .size(50.dp)
                .clip(RoundedCornerShape(500.dp)),
            contentScale = ContentScale.Crop,
            painter = painterResource(id = avatar),
            contentDescription = null
        )
        Column(modifier = modifier
            .padding(PaddingValues(start = 15.dp, top = 14.dp))
            .width(130.dp)
            .height(21.dp)
        ){
            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight.W500,
                fontFamily = fonts,
                color = Color(0xFFA684EE),
                lineHeight = 21.sp
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(name = "Budi Santoso")
}