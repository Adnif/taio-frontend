import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.rememberPagerState
import androidx.compose.foundation.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.google.accompanist.pager.*
import com.taio.taio.R
import com.taio.taio.ui.TandatanganioScreen
import com.taio.taio.ui.theme.Green500
import com.taio.taio.ui.theme.TandatanganioMobileTheme
import com.taio.taio.ui.theme.Typography

@OptIn(ExperimentalPagerApi::class)
@Composable
fun EmailCheckScreen(navController: NavController) {
    val pages = listOf(R.drawable.isi_form, R.drawable.ttd, R.drawable.generate)
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    BoxWithConstraints(modifier = Modifier
        .fillMaxSize()
        .background(color = White)
    ) {
        Column(modifier = Modifier
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .heightIn(min = maxHeight)
        ) {
            Row(
                modifier = Modifier
                    .height(45.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
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
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = stringResource(id = R.string.email_check),
                    style = Typography.h2,
                    textAlign = TextAlign.Center
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(375.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth(0.85f),
                    painter = painterResource(id = R.drawable.email_check_campaign),
                    contentDescription = "Email Check Campaign",
                )
            }
            
            Spacer(modifier = Modifier.size(40.dp))
            
            BottomLanding(label = stringResource(id = R.string.login_label)) {
                navController.popBackStack()
                navController.navigate(TandatanganioScreen.Login.route)
            }
        }
    }
}

@Composable
fun BottomLanding(
    label: String,
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
                    color = White
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(49.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Green500)
        )
    }
}

//@Preview
//@Composable
//fun EmailCheckScreenPreview() {
//    EmailCheckScreen()
//}