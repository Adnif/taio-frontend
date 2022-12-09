package com.taio.taio.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*
import com.taio.taio.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun LandingScreen(){
    val pages = listOf(R.drawable.isi_form,R.drawable.ttd,R.drawable.generate)
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ){


        Row(verticalAlignment = Alignment.CenterVertically){
            Image(
                painter = painterResource(id = R.drawable.logo_taio_rectangle),
                contentDescription = "Logo Taio",
                modifier = Modifier
                    .size(45.dp)
                    .shadow(elevation = 10.dp)
            )
            Text(
                text = "Tandatangan.io",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = "SKIP",
                fontSize = 15.sp,
                color = Color(0xFF615D5D),
                modifier = Modifier.clickable {
                    coroutineScope.launch{
                        pagerState.animateScrollToPage(2)
                    }
                }
            )
        }

    }

    Pages(pages = pages, pagerState = pagerState)
    Spacer(Modifier.size(16.dp))
    when(pagerState.currentPage){
        0 -> BottomLanding(label = "Next") {
            coroutineScope.launch{
                pagerState.animateScrollToPage(1)
            }
        }
        1 -> BottomLanding(label = "Next") {
            coroutineScope.launch{
                pagerState.animateScrollToPage(2)
            }
        }else -> {
            BottomLanding(label = "Register") {

            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Pages(
    pages: List<Int>,
    pagerState: PagerState
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "3 Langkah Mudah",
            fontWeight = FontWeight.Medium,
            fontSize = 30.sp,
            color = Color.Black
        )

        HorizontalPager(count = 3, state = pagerState) {position ->
            Image(
                painter = painterResource(id = pages[position]),
                contentDescription = "Pager Image"
            )
        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            activeColor = Color(0xFF27A74A),
            indicatorShape = RoundedCornerShape(10.dp),
            indicatorWidth = 30.dp,
            indicatorHeight = 10.dp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

    }
}

@Composable
fun BottomLanding(
    label: String,
    onButtonClick: () -> Unit
)
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Button(
            onClick = {
                onButtonClick()
            },
            content = {
                Text(text = label, color = Color.White)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(49.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF27A74A))
        )
        Spacer(Modifier.size(16.dp))
        Text(
            text = "Dengan menggunakan ttd.io Anda menyetujui aturan privacy dan policy kami",
            fontWeight = FontWeight.Medium,
            fontSize = 10.sp,
            color = Color(0xFF615D5D)
        )
        Spacer(Modifier.size(16.dp))
        Text(
            text = "Privacy & Policy",
            fontSize = 15.sp,
            color = Color(0xFF27A74A),
            modifier = Modifier.
            clickable {  }
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun LandingScreenPreview(){
    LandingScreen()
}