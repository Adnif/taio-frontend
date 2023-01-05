package com.taio.taio.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.taio.taio.R
import com.taio.taio.data.DataSource
import com.taio.taio.domain.model.User
import com.taio.taio.domain.model.UserRequest
import com.taio.taio.domain.model.UserRequested
import com.taio.taio.ui.TandatanganioScreen
import com.taio.taio.ui.theme.*
import com.taio.taio.viewmodel.SplashViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    authenticatedUser: User,
    modifier: Modifier = Modifier,
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
                style = Typography.h4,
                modifier = Modifier.padding(start = 15.dp)
            )
        }

        SearchBar(onSearchBarClick = {navController.navigate(TandatanganioScreen.Search.route)})
        Row(
            modifier = modifier
                .padding(PaddingValues(top = 30.dp))
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Icon(
                painter = painterResource(id = R.drawable.send),
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier.padding(end = 15.dp)
            )
            Text(
                text = stringResource(R.string.fast_request),
                style = Typography.h6,
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier
                    .clickable { },
                text = stringResource(R.string.more),
                style = Typography.body1,
                color = Gray700,
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
                text = stringResource(R.string.request),
                style = Typography.h6
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.clickable { navController.navigate(TandatanganioScreen.Request.route) },
                textAlign = TextAlign.End,
                text = stringResource(R.string.more),
                style = Typography.body1,
                color = Gray700,
            )
        }

        RequestList(requests = DataSource().loadRequest())

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
                text = stringResource(R.string.submission),
                style = Typography.h6
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                modifier = Modifier.clickable { navController.navigate(TandatanganioScreen.Submission.route) },
                textAlign = TextAlign.End,
                text = stringResource(R.string.more),
                style = Typography.body1,
                color = Gray700,
            )
        }

        RequestedList(requested = DataSource().loadRequested())
        Spacer(Modifier.height(35.dp))
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
            null,
            modifier
                .size(20.dp),
            Gray700
        )
        Text(
            text = stringResource(id = R.string.search_placeholder),
            modifier = modifier.padding(start = 5.dp),
            color = Gray700
        )
    }
}

@Composable
private fun RequestList(requests: List<UserRequest>){
    val requestSize = if (requests.size > 3){
        3
    }
    else {
        requests.size
    }
    Column {
        for (i in 0..requestSize-1) {
            Request(requests[i])
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
private fun RequestedList(requested: List<UserRequested>){
    val requestedSize = if (requested.size > 3){
        3
    }
    else {
        requested.size
    }
    Column{
        for (i in 0..requestedSize-1) {
            Requested(requested[i])
        }
    }
}

@Composable
fun FastRequest(user: User, modifier: Modifier = Modifier, onClick: () -> Unit){
    Row(
        modifier = modifier
            .clickable { onClick() }
            .padding(PaddingValues(top = 15.dp)),
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
        Text(
            text = user.name,
            style = Typography.body1,
            color = Blue500
        )
    }
}

@Composable
fun Request(request: UserRequest, modifier: Modifier = Modifier) {
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
                    style = Typography.h6
                )
                Text(
                    text = request.name,
                    style = Typography.body2,
                    color = Gray700,
                    modifier = modifier.padding(PaddingValues(top = 5.dp))
                )
                Text(
                    text = request.desc,
                    style = Typography.body2,
                    color = Gray700,
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
fun Requested(request: UserRequested, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 2.dp,
        modifier = Modifier
            .padding(top = 10.dp)
            .fillMaxWidth()
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
                    style = Typography.h6
                )
                Text(
                    text = request.name,
                    style = Typography.body2,
                    color = Gray700,
                    modifier = modifier.padding(PaddingValues(top = 5.dp))
                )
                if (request.status == 1){
                    Text(
                        text = stringResource(R.string.reject_message),
                        style = Typography.body1,
                        modifier = modifier.padding(PaddingValues(top = 5.dp))
                    )
                    Text(
                        text = request.rejectMessage,
                        style = Typography.body2,
                        color = Gray700,
                        modifier = modifier.padding(PaddingValues(top = 5.dp))
                    )
                }
                else {
                    Text(
                        text = request.desc,
                        style = Typography.body2,
                        color = Gray700,
                        modifier = modifier.padding(PaddingValues(top = 5.dp))
                    )
                }
                PreviewButton({})
                Row(
                    modifier = Modifier.padding(top = 18.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    if (request.status == 2) {
                        RequestedStatus(
                            text = stringResource(R.string.accepted),
                            MaterialTheme.colors.primary,
                            MaterialTheme.colors.onPrimary
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        RequestButton(
                            text = stringResource(R.string.download),
                            buttonColor = Blue500,
                            textColor = Color.White,
                            onClick = {}
                        )
                    }
                    if (request.status == 0){
                        RequestedStatus(
                            text = stringResource(R.string.sent),
                            Yellow500,
                            MaterialTheme.colors.onSecondary
                        )
                    }
                    if (request.status == 1){
                        RequestedStatus(
                            text = stringResource(R.string.rejected),
                            Gray700,
                            MaterialTheme.colors.onPrimary
                        )
                    }
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
        border = BorderStroke(1.dp, Blue500),
        modifier = Modifier.padding(top = 5.dp)
    ) {
        Icon(
            painterResource(id = R.drawable.document),
            contentDescription = null,
            Modifier,
            Blue500
        )
        Text(
            text = stringResource(R.string.document_preview),
            style = Typography.body1,
            color = Blue500,
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
            style = Typography.subtitle2,
            color = textColor
        )
    }
}

@Composable
fun RequestedStatus(
    text: String,
    background: Color,
    textColor: Color
) {
    Text(
        text = text,
        modifier = Modifier
            .border(BorderStroke(1.dp, MaterialTheme.colors.primary), Shapes.medium)
            .background(background)
            .padding(5.dp),
        color = textColor,
        style = Typography.caption
    )
}

//@Preview(showSystemUi = true)
//@Composable
//fun HomeScreenPreview(){
//    TandatanganioMobileTheme() {
//        val mockUser = User(R.drawable.avatar, "Asep Konco")
//        val navController = rememberNavController()
//        HomeScreen(authenticatedUser = mockUser, navController = navController)
//    }
//}
//
//@Preview(showSystemUi = true)
//@Composable
//fun RequestedPreview(){
//    TandatanganioMobileTheme() {
//        RequestedList(requested = DataSource().loadRequested())
//    }
//}