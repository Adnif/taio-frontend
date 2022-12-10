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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.taio.taio.data.DataSource
import com.taio.taio.ui.theme.TandatanganioMobileTheme
import com.taio.taio.domain.model.UserRequested

@Composable
fun SubmissionScreen() {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxWidth()
    ) {
        Text(
            text = "Pengajuan",
            fontSize = 24.sp,
            fontWeight = FontWeight.W500,
            color = Color(0xFF000000),
            lineHeight = 21.sp
        )
        val data = DataSource().loadRequested()
        SubmissionLongList(data)
    }
}

@Composable
fun SubmissionLongList(requested: List<UserRequested>){
    LazyColumn(modifier = Modifier){
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
