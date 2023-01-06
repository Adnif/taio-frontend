package com.taio.taio.ui.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.taio.taio.R
import com.taio.taio.domain.model.User
import com.taio.taio.ui.TandatanganioScreen
import com.taio.taio.ui.theme.Gray700
import com.taio.taio.ui.theme.TandatanganioMobileTheme
import com.taio.taio.ui.theme.Typography

@Composable
fun ProfileScreen(
    user: User,
    navController: NavHostController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .padding(bottom = 5.dp)
        ) {
            Text(
                text = stringResource(R.string.profile),
                color = Color.Black,
                style = Typography.h3
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.edit_profile),
                contentDescription = null,
                tint = MaterialTheme.colors.primary,
                modifier = Modifier
                    .size(30.dp)
                    .clickable {
                        navController.navigate(TandatanganioScreen.EditProfile.route)
                    }
            )
        }
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
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .border(BorderStroke(1.dp, Color.LightGray))
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
                    .border(BorderStroke(1.dp, Color.LightGray))
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
                    text = stringResource(R.string.generate_request) + user.requested.size.toString(),
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
        Spacer(modifier = Modifier.padding(15.dp))
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.other),
                style = Typography.h3
            )
            Row(
                modifier = Modifier.padding(vertical = 5.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.privacy_policy),
                    contentDescription = null,
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .size(20.dp)
                )
                Text(
                    text = stringResource(R.string.privacy_policy),
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
                    painter = painterResource(id = R.drawable.logout),
                    contentDescription = null,
                    tint = MaterialTheme.colors.error,
                    modifier = Modifier
                        .size(20.dp)
                )
                Text(
                    text = stringResource(R.string.logout),
                    style = Typography.body2,
                    modifier = Modifier.padding(horizontal = 2.dp),
                    color = MaterialTheme.colors.error
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.slide_up),
                    contentDescription = null,
                    tint = MaterialTheme.colors.error,
                    modifier = Modifier
                        .size(20.dp)
                )
            }
            Spacer(Modifier.height(35.dp))
        }
    }
}

//@Preview (showSystemUi = true)
//@Composable
//fun ProfileScreenPreview() {
//    TandatanganioMobileTheme {
//        val mockUser = User(R.drawable.avatar, "Asep Konco")
//        val navController = rememberNavController()
//        ProfileScreen(mockUser, navController)
//    }
//}