package com.taio.taio.ui.screen

import android.view.RoundedCorner
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import com.taio.taio.R
import com.taio.taio.ui.theme.Blue500
import com.taio.taio.ui.theme.Gray700
import com.taio.taio.ui.theme.Green500
import com.taio.taio.ui.theme.Typography
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun LandingScreen(){
    val pages = listOf(R.drawable.isi_form,R.drawable.ttd,R.drawable.generate)
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .heightIn(min = maxHeight)
                .padding(16.dp),
        ){
            Column(
            ){
                Row(verticalAlignment = Alignment.CenterVertically){
                    Image(
                        painter = painterResource(id = R.drawable.logo_taio_rectangle),
                        contentDescription = stringResource(R.string.logo_taio),
                        modifier = Modifier
                            .size(45.dp)
                            .shadow(elevation = 2.dp)
                            .padding(end = 10.dp),
                    )
                    Text(
                        text = stringResource(id = R.string.name),
                        style = Typography.h5,
                        color = Color.Black
                    )
                    Spacer(Modifier.weight(1f))
                    Text(
                        text = stringResource(id = R.string.skip),
                        style = Typography.subtitle2,
                        color = Gray700,
                        modifier = Modifier.clickable {
                            coroutineScope.launch{
                                pagerState.animateScrollToPage(2)
                            }
                        }
                    )
                }

            }
            Spacer(Modifier.weight(1f))
            Column(
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ){
                Text(
                    text = stringResource(id = R.string.landing_header),
                    style = Typography.h2,
                    color = Color.Black
                )

            }
            Spacer(Modifier.weight(1f))
            Pages(pages = pages, pagerState = pagerState)
            Spacer(Modifier.weight(1f))
            Column(
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ){
                PageIndicator(totalPages = pagerState.pageCount, currentPage = pagerState.currentPage)

            }
            Spacer(Modifier.size(16.dp))
            when(pagerState.currentPage){
                0 -> BottomLanding(label = stringResource(id = R.string.next_button)) {
                    coroutineScope.launch{
                        pagerState.animateScrollToPage(1)
                    }
                }
                1 -> BottomLanding(label = stringResource(id = R.string.next_button)) {
                    coroutineScope.launch{
                        pagerState.animateScrollToPage(2)
                    }
                }else -> {
                BottomLanding(label = stringResource(id = R.string.sign_up)) {

                }
            }
            }
        }
    }



}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Pages(
    pages: List<Int>,
    pagerState: PagerState,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ){

        HorizontalPager(count = 3, state = pagerState) {position ->
            Image(
                painter = painterResource(id = pages[position]),
                contentDescription = stringResource(id = R.string.content_description_pager)
            )
        }

    }
}

@Composable
fun PageIndicator(
    totalPages: Int,
    currentPage: Int,
    modifier: Modifier = Modifier,
    indicatorSize: Dp = 10.dp,
    color: Color = Green500,
    spacing: Dp = indicatorSize,
    selectedMultiplier: Int = 3
){
    val rowWidth = (indicatorSize * (selectedMultiplier + (totalPages - 1))) + (spacing * (totalPages - 1))
    Column() {
        Row(
            modifier = modifier
                .requiredWidth(rowWidth),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            for (i in 0 until totalPages) {
                val selected = i == currentPage

                val height = indicatorSize
                val width: Dp by animateDpAsState(
                    if (selected) indicatorSize * selectedMultiplier else indicatorSize
                )
                Canvas(
                    modifier = Modifier
                        .size(width, height),
                    onDraw = {
                        drawRoundRect(
                            color = if (selected) Green500 else Green500.copy(0.5f),
                            cornerRadius = CornerRadius(height.toPx() / 2),
                            size = Size(width.toPx(), height.toPx())
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun BottomLanding(
    label: String,
    modifier: Modifier = Modifier,
    onButtonClick: () -> Unit
)
{
    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Button(
            onClick = {
                onButtonClick()
            },
            content = {
                Text(
                    text = label,
                    style = Typography.button,
                    color = Color.White
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(49.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Green500)
        )
        Spacer(Modifier.size(16.dp))
        Text(
            text = stringResource(id = R.string.agreement_privacy_policy),
            style = Typography.overline,
            color = Gray700,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(Modifier.size(16.dp))
        Text(
            text = stringResource(id = R.string.privacy_policy),
            style = Typography.subtitle1,
            color = Green500,
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