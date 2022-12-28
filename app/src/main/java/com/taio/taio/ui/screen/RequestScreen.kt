package com.taio.taio.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.taio.taio.data.DataSource
import com.taio.taio.domain.model.UserRequest
import com.taio.taio.ui.theme.TandatanganioMobileTheme
import com.taio.taio.ui.theme.Typography
import com.taio.taio.R

@Composable
fun RequestScreen() {
    val data = DataSource().loadRequest()
    RequestLongList(data)
}

@Composable
fun RequestLongList(requestList: List<UserRequest>) {
    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
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
    }
}

@Preview(showSystemUi = true)
@Composable
fun RequestScreenPreview(){
    TandatanganioMobileTheme {
        RequestScreen()
    }
}