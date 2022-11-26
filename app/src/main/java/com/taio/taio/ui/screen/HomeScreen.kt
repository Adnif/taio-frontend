package com.taio.taio.ui.theme.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
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
import com.taio.taio.data.DataSource
import com.taio.taio.model.FastRequest
import com.taio.taio.model.Request
import com.taio.taio.ui.theme.*

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    name: String
){
    Column() {
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
            SearchBar(text = "Search", onTextChange = {}, onCloseClicked = {}, onSearchClicked = {})
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
                    .padding(PaddingValues(end = 131.dp))
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
                    .padding(PaddingValues(end = 20.dp))
            ) {
                Text(
                    modifier = Modifier.clickable {  },
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
        FastReqList(fastReqList = DataSource().loadFastRequest())

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
                    .padding(PaddingValues(end = 20.dp))
            ) {
                Text(
                    modifier = Modifier.clickable {  },
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
        ReqList(nReqList = DataSource().loadRequest(), modifier)
    }
}

@Composable
fun SearchBar(
    text: String,
    onTextChange: (String) -> Unit,
    onCloseClicked: () -> Unit,
    onSearchClicked: (String) -> Unit
){
   OutlinedTextField(
       modifier = Modifier
           .width(342.dp)
           .height(48.dp)
           .clip(RoundedCornerShape(5.dp)),
       value = text,
       onValueChange = {
           onTextChange
       },
       placeholder = {
           Text(text = "Mau minta tandatangan siapa ?")
       },
       textStyle = TextStyle(
           fontSize = 15.sp
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
                   if (text.isEmpty()){
                       onTextChange("")
                   }else {
                       onCloseClicked()
                   }
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
private fun ReqList(nReqList: List<Request>, modifier: Modifier = Modifier){
    var displayReq: Int
    if (nReqList.size > 3){
        displayReq = 3
    }
    else {
        displayReq = nReqList.size
    }
    LazyColumn {
        items(displayReq) { reqList ->
            Requested(nReqList[reqList], modifier)
        }
    }
}

@Composable
private fun FastReqList(fastReqList: List<FastRequest>, modifier: Modifier = Modifier){
    var displayReq: Int
    if (fastReqList.size > 3){
        displayReq = 3
    }
    else {
        displayReq = fastReqList.size
    }
    LazyColumn {
        items(displayReq) { reqList ->
            FastReq(fastReqList[reqList], modifier)
        }
    }
}

@Composable
fun FastReq(fastRequest: FastRequest, modifier: Modifier = Modifier){
    Row(
        modifier = modifier
            .padding(PaddingValues(start = 24.dp, top = 17.dp))
    ) {
        Image(
            modifier = modifier
                .size(50.dp)
                .clip(RoundedCornerShape(500.dp)),
            contentScale = ContentScale.Crop,
            painter = painterResource(fastRequest.avatar),
            contentDescription = null
        )
        Column(modifier = modifier
            .padding(PaddingValues(start = 15.dp, top = 14.dp))
            .width(130.dp)
            .height(21.dp)
        ){
            Text(
                text = fastRequest.userName,
                fontSize = 14.sp,
                fontWeight = FontWeight.W500,
                fontFamily = fonts,
                color = Color(0xFFA684EE),
                lineHeight = 21.sp
            )
        }
    }
}

@Composable
fun Requested(normalRequest: Request, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .border(BorderStroke(0.dp, Color.White), shape = Shapes.small)
            .padding(PaddingValues(15.dp, 5.dp))
    ) {
        Row(
            modifier = modifier
                .padding(PaddingValues(start = 15.dp, top = 17.dp))
        ) {
            Image(
                modifier = modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(500.dp)),
                contentScale = ContentScale.Crop,
                painter = painterResource(normalRequest.avatar),
                contentDescription = null
            )
            Column(
                modifier = modifier
                    .padding(PaddingValues(start = 15.dp, top = 10.dp))
                    .width(300.dp)
            ) {
                Text(
                    text = normalRequest.docTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
                Text(
                    text = normalRequest.userName,
                    fontSize = 14.sp,
                    modifier = modifier.padding(PaddingValues(top = 5.dp))
                )
                Text(
                    text = normalRequest.docDesc,
                    fontSize = 14.sp,
                    modifier = modifier.padding(PaddingValues(top = 5.dp))
                )
                PreviewButton()
                Spacer(modifier = Modifier.height(20.dp))
                Row {
                    Spacer(modifier = Modifier.width(70.dp))
                    RejectButton()
                    AcceptButton()
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }

}

@Composable
fun PreviewButton() {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.White
        ),
        border = BorderStroke(1.dp, Green200),
        modifier = Modifier
            .width(150.dp)
            .height(50.dp)
            .padding(PaddingValues(top = 20.dp))
    ) {
        Image(
            painterResource(id = R.drawable.document),
            contentDescription = null,
            modifier = Modifier
                .size(15.dp)
                .padding(PaddingValues(end = 5.dp))
        )
        Text(
            text = "Preview Dokumen",
            color = Purple200,
            fontSize = 9.sp
        )
    }
}

@Composable
fun RejectButton() {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(Color.White),
        modifier = Modifier
            .width(100.dp)
            .height(40.dp)
    ){
        Text(
            text = "Tolak",
            fontSize = 18.sp,
            fontWeight = FontWeight.W400,
            fontFamily = fonts,
            color = Color(0xFF615D5D),
        )
    }
}

@Composable
fun AcceptButton() {
    Button(
        onClick = {},
        colors = ButtonDefaults.buttonColors(Green500),
        modifier = Modifier
            .width(100.dp)
            .height(40.dp)
            .padding(PaddingValues(start = 5.dp))
    ){
        Text(
            text = "Terima",
            fontSize = 15.sp,
            fontWeight = FontWeight.W400,
            fontFamily = fonts,
            color = Color.White
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(name = "Asep Konco")
}