package com.taio.taio.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.taio.taio.R
import com.taio.taio.data.DataSource
import com.taio.taio.ui.theme.TandatanganioMobileTheme
import com.taio.taio.domain.model.UserRequested
import com.taio.taio.ui.theme.Typography

@Composable
fun SubmissionScreen() {
    val data = DataSource().loadRequested()
    SubmissionLongList(data)
}

@Composable
fun SubmissionLongList(requested: List<UserRequested>){
    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)){
        item{
            Text(
                text = stringResource(R.string.submission),
                style = Typography.h3,
                color = Color.Black,
            )
        }
        items(items = requested) { request ->
            Requested(request = request)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SubmissionScreenPreview(){
    TandatanganioMobileTheme {
        SubmissionScreen()
    }
}
