package com.taio.taio.ui.screen

import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.taio.taio.R
import com.taio.taio.domain.model.User
import com.taio.taio.ui.theme.*
import com.taio.taio.viewmodel.SearchViewModel

@Composable
fun OtherProfileScreen(
    user: User,
    navController: NavHostController,
    searchViewModel: SearchViewModel = viewModel()
){
    var followed = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(user.userName) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(20.dp))
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .align(alignment = Alignment.CenterHorizontally),
                contentScale = ContentScale.Crop,
                painter = painterResource(user.avatar),
                contentDescription = null
            )
            Text(
                text = user.name,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),
                style = Typography.h6
            )
            Text(
                text = user.email,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),
                style = Typography.body2,
                color = Gray700
            )
            Text(
                text = user.phone,
                modifier = Modifier
                    .align(alignment = Alignment.CenterHorizontally),
                style = Typography.body2,
                color = Gray700
            )

            Spacer(modifier = Modifier.padding(20.dp))
            Row(
                modifier = Modifier
                .align(Alignment.CenterHorizontally)
            ){
                OutlinedButton(
                    onClick = {
                              followed.value = !followed.value
                    },
                    border = if(followed.value) BorderStroke(1.dp, Green500) else null,
                    content = {
                        Row{
                            Icon(
                                painter = painterResource(id = R.drawable.generated_count),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .size(20.dp),
                            )
                            if(followed.value){
                                Text(
                                    text = "diikuti",
                                    style = Typography.body2,
                                    color = Green500,
                                    modifier = Modifier.padding(start = 10.dp)
                                )
                            }else{
                                Text(
                                    text = "ikuti",
                                    style = Typography.body2,
                                    color = Color.White,
                                    modifier = Modifier.padding(start = 10.dp)
                                )

                            }
                        }
                    },
                    modifier = Modifier
                        .width(121.dp)
                        .height(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = if(followed.value) Gray300 else Green500
                    ),
                    shape = RoundedCornerShape(20.dp),
                    contentPadding = PaddingValues(0.dp)
                )
                Spacer(Modifier.width(10.dp))
                OutlinedButton(
                    onClick = {
                    },
                    content = {
                        Row{
                            Icon(
                                painter = painterResource(id = R.drawable.generated_count),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .size(20.dp),
                            )
                            Text(
                                text = "Minta Ttd",
                                style = Typography.body2,
                                color = Color.White,
                                modifier = Modifier.padding(start = 10.dp)
                            )
                        }
                    },
                    modifier = Modifier
                        .width(121.dp)
                        .height(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Green500
                    ),
                    shape = RoundedCornerShape(20.dp),
                    contentPadding = PaddingValues(0.dp)
                )
            }
            Spacer(modifier = Modifier.padding(20.dp))
            Row(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .border(BorderStroke(1.dp, Color.LightGray)),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = user.follower.size.toString(),
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally),
                        style = Typography.h1
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.follower),
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = stringResource(R.string.follower),
                        style = Typography.subtitle2,
                        color = Gray700,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                    )
                }
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .border(BorderStroke(1.dp, Color.LightGray)),
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Text(
                        text = user.following.size.toString(),
                        modifier = Modifier
                            .align(alignment = Alignment.CenterHorizontally),
                        style = Typography.h1
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.following),
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .size(50.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                    Text(
                        text = stringResource(R.string.following),
                        style = Typography.subtitle2,
                        color = Gray700,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                    )
                }
            }
            Spacer(modifier = Modifier.padding(15.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ) {
                Text(
                    text = stringResource(R.string.statistic),
                    style = Typography.h3
                )
                Row(
                    modifier = Modifier.padding(vertical = 5.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.generated_count),
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .size(20.dp),
                    )
                    Text(
                        text = stringResource(R.string.generate_count) + user.tergenerate.size.toString(),
                        style = Typography.body2,
                        modifier = Modifier.padding(horizontal = 2.dp)
                    )
                    Spacer(Modifier.weight(1f))
                    Icon(
                        painter = painterResource(id = R.drawable.slide_up),
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
                Row(
                    modifier = Modifier.padding(vertical = 5.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.selfmade_count),
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Text(
                        text = stringResource(R.string.generate_self) + user.self.size.toString(),
                        style = Typography.body2,
                        modifier = Modifier.padding(horizontal = 2.dp)
                    )
                    Spacer(Modifier.weight(1f))
                    Icon(
                        painter = painterResource(id = R.drawable.slide_up),
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
                Row(
                    modifier = Modifier.padding(vertical = 5.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.requestmade_count),
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Text(
                        text = stringResource(R.string.generate_request) + user.requested.size.toString(), //Number i variable
                        style = Typography.body2,
                        modifier = Modifier.padding(horizontal = 2.dp)
                    )
                    Spacer(Modifier.weight(1f))
                    Icon(
                        painter = painterResource(id = R.drawable.slide_up),
                        contentDescription = null,
                        tint = MaterialTheme.colors.primary,
                        modifier = Modifier
                            .size(20.dp)
                    )
                }
            }
        }
    }
}
