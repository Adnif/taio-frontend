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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.taio.taio.R
import com.taio.taio.domain.model.User
import com.taio.taio.ui.theme.TandatanganioMobileTheme
import com.taio.taio.ui.theme.Typography

@Composable
fun ProfileScreen(user: User) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .padding(bottom = 5.dp)
        ) {
            Text(
                text = "Profil",
                fontSize = 24.sp,
                fontWeight = FontWeight.W500,
                color = Color(0xFF000000),
                lineHeight = 21.sp,
                style = Typography.h1
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.edit_profile),
                contentDescription = "Edit Profil",
                tint = MaterialTheme.colors.primary,
                modifier = Modifier
                    .size(30.dp)
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
            style = Typography.h1
        )
        Text(
            text = user.email,
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally),
            style = Typography.caption
        )
        Text(
            text = "087770775311",
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally),
            style = Typography.caption
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
                    text = "37",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally),
                    style = Typography.h1
                )
                Icon(
                    painter = painterResource(id = R.drawable.follower),
                    contentDescription = "Pengikut",
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Pengikut",
                    style = Typography.caption,
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
                    text = "909",
                    modifier = Modifier
                        .align(alignment = Alignment.CenterHorizontally),
                    style = Typography.h1
                )
                Icon(
                    painter = painterResource(id = R.drawable.following),
                    contentDescription = "Pengikut",
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .size(50.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Text(
                    text = "Mengikuti",
                    style = Typography.caption,
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
                text = "Statistik",
                style = Typography.h1
            )
            Row(
                modifier = Modifier.padding(vertical = 5.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.generated_count),
                    contentDescription = "Tandatangan tergenerate",
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .size(20.dp),
                )
                Text(
                    text = "Tandatangan ter-generate: 3", //Number i variable
                    style = Typography.caption,
                    modifier = Modifier.padding(horizontal = 2.dp)
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.slide_up),
                    contentDescription = "Lebih lengkap",
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
                    contentDescription = "Dibuat secara mandiri",
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .size(20.dp)
                )
                Text(
                    text = "Dibuat secara mandiri: 1", //Number i variable
                    style = Typography.caption,
                    modifier = Modifier.padding(horizontal = 2.dp)
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.slide_up),
                    contentDescription = "Lebih lengkap",
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
                    contentDescription = "Dibuat melalui permintaan",
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .size(20.dp)
                )
                Text(
                    text = "Dibuat melalui permintaan: 1", //Number i variable
                    style = Typography.caption,
                    modifier = Modifier.padding(horizontal = 2.dp)
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.slide_up),
                    contentDescription = "Lebih lengkap",
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
                text = "Lainnya",
                style = Typography.h1
            )
            Row(
                modifier = Modifier.padding(vertical = 5.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.privacy_policy),
                    contentDescription = "Edit Profil",
                    tint = MaterialTheme.colors.primary,
                    modifier = Modifier
                        .size(20.dp)
                )
                Text(
                    text = "Privacy & Policy",
                    style = Typography.caption,
                    modifier = Modifier.padding(horizontal = 2.dp)
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.slide_up),
                    contentDescription = "Edit Profil",
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
                    contentDescription = "Edit Profil",
                    tint = MaterialTheme.colors.error,
                    modifier = Modifier
                        .size(20.dp)
                )
                Text(
                    text = "Logout",
                    style = Typography.caption,
                    modifier = Modifier.padding(horizontal = 2.dp),
                    color = MaterialTheme.colors.error
                )
                Spacer(Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.slide_up),
                    contentDescription = "Edit Profil",
                    tint = MaterialTheme.colors.error,
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        }
    }
}

@Preview (showSystemUi = true)
@Composable
fun ProfileScreenPreview() {
    TandatanganioMobileTheme {
        val mockUser = User(R.drawable.avatar, "Asep Konco")
        ProfileScreen(mockUser)
    }
}