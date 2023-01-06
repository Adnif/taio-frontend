package com.taio.taio.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.taio.taio.R
import com.taio.taio.data.DataSource
import com.taio.taio.domain.model.UserRequest
import com.taio.taio.ui.theme.TandatanganioMobileTheme
import com.taio.taio.ui.theme.Typography

@Composable
fun RequestScreen(
    navController: NavController,
) {
    val data = DataSource().loadRequest()
    RequestLongList(data)
}

@Composable
fun RequestLongList(requestList: List<UserRequest>) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item{
            Text(
                text = stringResource(R.string.request),
                style = Typography.h3,
                color = Color.Black,
            )
        }
        items(items = requestList) { request ->
            Request(request = request)
        }
        item{
            Spacer(Modifier.height(35.dp))
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun RequestScreenPreview(){
    val navController = rememberNavController()
    TandatanganioMobileTheme {
        RequestScreen(navController)
    }
}