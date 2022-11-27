package com.taio.taio.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.sharp.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.taio.taio.R
import com.taio.taio.data.DataSource
import com.taio.taio.domain.model.User
import com.taio.taio.domain.model.UserRequest
import com.taio.taio.ui.theme.*

@Composable
fun HomeScreen(
    authenticatedUser: User,
    modifier: Modifier = Modifier
){
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        Row(
            modifier = modifier
                .padding(top = 21.dp, bottom = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = modifier
                    .size(74.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop,
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = null
            )
            Text(
                text = stringResource(R.string.greeter, authenticatedUser.name),
                style = Typography.h1,
                modifier = Modifier.padding(start = 15.dp)
            )
        }

        SearchBar(onSearchBarClick = {})

        Row(
            modifier = modifier
                .padding(PaddingValues(top = 30.dp))
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Icon(
                painter = painterResource(id = R.drawable.send),
                contentDescription = "Send Icon",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.padding(end = 15.dp)
            )
            Text(
                text = "Minta Cepat",
                fontSize = 14.sp,
                fontWeight = FontWeight.W500,
                color = Color(0xFF000000),
                lineHeight = 21.sp
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .clickable { },
                text = "Selengkapnya",
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                color = Color(0xFF615D5D),
                lineHeight = 18.sp,
            )
        }

        FastRequestList(userList = DataSource().loadFastRequest(), onClick = {})

        Row(
            modifier = modifier
                .padding(top = 30.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(id = R.drawable.request_bold),
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.padding(end = 15.dp)
            )
            Text(
                text = "Permintaan",
                fontSize = 14.sp,
                fontWeight = FontWeight.W500,
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.clickable {  },
                textAlign = TextAlign.End,
                text = "Selengkapnya",
                fontSize = 12.sp,
                fontWeight = FontWeight.W400,
                color = Color(0xFF615D5D),
                lineHeight = 18.sp,
            )
        }

        RequestedList(requests = DataSource().loadRequest())
    }
}

@Composable
fun SearchBar(
    onSearchBarClick: () -> Unit,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .border(
                BorderStroke(2.dp, MaterialTheme.colors.primary),
                shape = RoundedCornerShape(5.dp),
            )
            .clickable { onSearchBarClick() }
            .padding(PaddingValues(horizontal = 10.dp, vertical = 15.dp))
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            Icons.Sharp.Search,
            "search icon",
            modifier
                .size(20.dp)
        )
        Text(
            text = stringResource(id = R.string.search_placeholder),
            modifier = modifier.padding(start = 5.dp)
        )
    }
}

@Composable
private fun RequestedList(requests: List<UserRequest>){
    val requestSize = if (requests.size > 3){
        3
    }
    else {
        requests.size
    }
    Column {
        for (i in 0..requestSize-1) {
            Requested(requests[i])
        }
    }
}

@Composable
private fun FastRequestList(userList: List<User>, modifier: Modifier = Modifier, onClick: () -> Unit){
    val size = if (userList.size > 3){
        3
    } else {
        userList.size
    }
    Column {
        for (i in 0..size-1) {
            FastRequest(userList[i], modifier, onClick)
        }
    }
}

@Composable
fun FastRequest(user: User, modifier: Modifier = Modifier, onClick: () -> Unit){
    Row(
        modifier = modifier
            .clickable { onClick() }
            .padding(PaddingValues(top = 15.dp))
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
        Text(
            text = user.name,
            style = Typography.caption,
            color = Color(0xFFA684EE),
            lineHeight = 21.sp
        )
    }
}

@Composable
fun Requested(request: UserRequest, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 2.dp,
        modifier = Modifier
            .padding(top = 10.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                modifier = modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(500.dp)),
                contentScale = ContentScale.Crop,
                painter = painterResource(request.avatar),
                contentDescription = null
            )
            Column(
                modifier = modifier
                    .padding(start = 15.dp)
            ) {
                Text(
                    text = request.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
                Text(
                    text = request.name,
                    style = Typography.caption,
                    modifier = modifier.padding(PaddingValues(top = 5.dp))
                )
                Text(
                    text = request.desc,
                    style = Typography.caption,
                    modifier = modifier.padding(PaddingValues(top = 5.dp))
                )
                PreviewButton({})
                Row(
                    modifier = Modifier.padding(top = 18.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Spacer(modifier = Modifier.weight(1f))
                    RequestButton(
                        text = stringResource(R.string.reject),
                        buttonColor = Color.White,
                        onClick = {},
                        modifier = Modifier.padding(end = 30.dp)
                    )
                    RequestButton(
                        text = stringResource(R.string.accept),
                        buttonColor = MaterialTheme.colors.primary,
                        textColor = Color.White,
                        onClick = {}
                    )
                }
            }
        }
    }

}

@Composable
fun PreviewButton(onPreviewClick: () -> Unit) {
    Button(
        onClick = onPreviewClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
        modifier = Modifier.padding(top = 5.dp)
    ) {
        Image(
            painterResource(id = R.drawable.document),
            contentDescription = null,
        )
        Text(
            text = stringResource(R.string.document_preview),
            color = Purple200,
            modifier = Modifier
                .padding(start = 5.dp)
        )
    }
}

@Composable
fun RequestButton(
    modifier: Modifier = Modifier,
    text: String,
    buttonColor: Color,
    textColor: Color = Color.Black,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(buttonColor),
        modifier = modifier
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Light,
            color = textColor
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview(){
    TandatanganioMobileTheme() {
        val mockUser = User(R.drawable.avatar, "Asep Konco")
        HomeScreen(mockUser)
    }
}