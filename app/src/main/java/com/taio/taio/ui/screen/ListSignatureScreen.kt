package com.taio.taio.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.taio.taio.data.DataSource
import com.taio.taio.R
import com.taio.taio.domain.model.SiganatureList
import com.taio.taio.ui.TandatanganioScreen
import com.taio.taio.ui.theme.*

@Composable
fun ListSignatureScreen(navController: NavController){
    val dataSignature = DataSource().loadListSignature()
    Column() {
        Column() {
            BannerCreate(navController = navController)
        }
        Column {
            ListSignature(dataSignature)
        }
    }

}


@Composable
fun BannerCreate(navController: NavController){
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())
        .fillMaxHeight()
        .padding(top = 30.dp, bottom = 30.dp)) {

        Card(
            shape = RoundedCornerShape(10.dp),
            backgroundColor = Green500,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .defaultMinSize(400.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier
                        .padding(PaddingValues(bottom = 5.dp))
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.generated_count),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.padding(end = 15.dp)
                    )
                    Text(
                        text = "Buat Tanda Tangan",
                        style = Typography.h4,
                        color = Color.White
                    )
                }
                Button(
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                    onClick = {navController.navigate(
                    TandatanganioScreen.Create.route)}
                ) {
                    Text(
                        text = "Ayo!",
                        style = Typography.h6,
                        color = Green500
                    )
                    
                }
            }
        }
    }
}

@Composable
fun ListSignature(siganatureList: List<SiganatureList>){
    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
        item{
            Text(
                text = "Tandatangan yang sudah dibuat",
                style = Typography.h6,
                color = Color.Black,
            )
        }
        items(items = siganatureList) { request ->
            ShapeListSignature(siganatureList = request)
        }
    }
}

@Composable
fun ShapeListSignature(siganatureList: SiganatureList, modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(12.dp),
        elevation = 2.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = modifier
                    .padding(start = 15.dp)
            ) {
                Text(
                    text = siganatureList.documentName,
                    style = Typography.h6
                )
                Text(
                    text = siganatureList.documentDate,
                    style = Typography.body2,
                    color = Gray700,
                    modifier = modifier.padding(PaddingValues(top = 5.dp))
                )
                Text(
                    text = siganatureList.documentDescription,
                    style = Typography.body2,
                    color = Gray700,
                    modifier = modifier.padding(PaddingValues(top = 5.dp))
                )
                Row(modifier = Modifier.padding(top = 18.dp),
                    horizontalArrangement = Arrangement.End) {
                    Button(
                        colors = ButtonDefaults.buttonColors(backgroundColor = Green500),
                        modifier = Modifier.padding(start = 180.dp),
                        onClick = {}
                    ) {
                        Icon(
                            painterResource(id = R.drawable.generated_count),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.padding(end = 5.dp)
                        )
                        Text(
                            text = "Unduh",
                            style = Typography.h6,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}
