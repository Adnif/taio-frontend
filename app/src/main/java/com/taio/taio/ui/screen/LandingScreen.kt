package com.taio.taio.ui.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.*
import com.taio.taio.R
import com.taio.taio.ui.TandatanganioScreen
import com.taio.taio.ui.theme.Gray700
import com.taio.taio.ui.theme.Green500
import com.taio.taio.ui.theme.Typography
import com.taio.taio.viewmodel.SplashViewModel
import kotlinx.coroutines.launch

sealed class LandingPage(
    val image:Int,
    val title:Int,
    val desc:Int
){
    object First: LandingPage(
        image = R.drawable.isi_form,
        title = R.string.landing_title_first,
        desc = R.string.landing_desc_first
    )
    object Second: LandingPage(
        image = R.drawable.ttd,
        title = R.string.landing_title_second,
        desc = R.string.landing_desc_second
    )
    object Third: LandingPage(
        image = R.drawable.generate,
        title = R.string.landing_title_third,
        desc = R.string.landing_desc_third
    )
}
@OptIn(ExperimentalPagerApi::class)
@Composable
fun LandingScreen(
    navController: NavHostController,
){
    val pages = listOf(
        LandingPage.First,
        LandingPage.Second,
        LandingPage.Third
    )
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
                    Card(
                        shape = RoundedCornerShape(12.dp),
                        elevation = 10.dp,
                        modifier = Modifier
                            .size(45.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_taio_rectangle),
                            contentDescription = stringResource(R.string.logo_taio),
                        )
                    }
                    Spacer(Modifier.width(16.dp))
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
            Spacer(Modifier.height(16.dp))
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
                    navController.popBackStack()
                    navController.navigate(TandatanganioScreen.Register.route)
                }
            }
            }
        }
    }



}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Pages(
    pages: List<LandingPage>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ){

        HorizontalPager(count = 3, state = pagerState) {position ->
            Column(horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.SpaceEvenly){
                Spacer(Modifier.height(16.dp))
                Image(
                    modifier = modifier
                        .size(70.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop,
                    painter = painterResource(id = pages[position].image),
                    contentDescription = stringResource(id = R.string.content_description_pager)
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = stringResource(pages[position].title),
                    style = Typography.h3,
                    color = Color.Black
                )
                Spacer(Modifier.height(16.dp))
                Text(
                    text = stringResource(pages[position].desc),
                    style = Typography.subtitle1,
                    color = Gray700,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(16.dp))

            }
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
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.size(16.dp))
        Text(
            text = stringResource(id = R.string.privacy_policy),
            style = Typography.subtitle1,
            color = Green500,
            modifier = Modifier.
            clickable {
            }
        )
    }
}

@Composable
@Preview(showSystemUi = true)
fun LandingScreenPreview(){
    val navController = rememberNavController()
    LandingScreen(navController)
}