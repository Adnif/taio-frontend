import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.taio.taio.R
import com.taio.taio.domain.model.User
import com.taio.taio.ui.TandatanganioScreen
import com.taio.taio.ui.theme.Typography

@Composable
fun EditProfileScreen(user: User, navController: NavHostController,) {
    BoxWithConstraints(modifier = Modifier
        .fillMaxSize()
        .background(color = White)
    ) {
//        val editProfileState: editProfileState = viewModel.editProfileState.collectAsState().value
        Column( modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
        ) {
            Row() {
                Text(
                    text = stringResource(id = R.string.edit_profile),
                    style = Typography.h2
                )
            }

            // Profile Image, User Name, Email & Phone
            Column( modifier = Modifier
                .fillMaxWidth()
                .height(190.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .size(125.dp)
                        .clip(CircleShape),
                    painter = painterResource(id = user.avatar),
                    contentDescription = "Logo User",
                )
                Text(
                    text = user.name,
                    style = Typography.h3
                )
                Text(
                    text = user.email,
                    style = Typography.caption
                )
                Text(
                    text = "+62878874856381",
                    style = Typography.caption
                )
            }

            // Edit Form Section
            Column( modifier = Modifier
                .fillMaxWidth()
                .height(425.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column() {
                    val spaceValue: Modifier = Modifier.size(15.dp)
                    val formModifier: Modifier = Modifier
                        .fillMaxWidth()
                    val focusManager = LocalFocusManager.current
                    val name = remember { mutableStateOf(TextFieldValue()) }
                    val phone = remember { mutableStateOf(TextFieldValue()) }
                    val about = remember { mutableStateOf(TextFieldValue()) }

                    // Field 1 : Name
                    Column() {
                        Text(
                            modifier = Modifier.padding(bottom = 5.dp),
                            text = "Nama Lengkap",
                            fontSize = 15.sp,
                            style = Typography.caption
                        )

                        OutlinedTextField(
                            modifier = formModifier,
                            shape = RoundedCornerShape(5.dp),
                            value = name.value,
                            onValueChange = {
                                name.value = it
                            },
                            placeholder = { Text(text = user.name) },
                        )
                    }

                    Spacer(modifier = spaceValue)

                    // Field 2 : Phone
                    Column() {
                        Text(
                            modifier = Modifier.padding(bottom = 5.dp),
                            text = "Nomor Handphone",
                            fontSize = 15.sp,
                            style = Typography.caption
                        )

                        OutlinedTextField(
                            modifier = formModifier,
                            shape = RoundedCornerShape(5.dp),
                            value = phone.value,
                            onValueChange = {
                                phone.value = it
                            },
                            placeholder = { Text(text = "+62878874856381") },
                        )
                    }

                    Spacer(modifier = spaceValue)

                    // Field 3 : About Me
                    Column() {
                        Text(
                            modifier = Modifier.padding(bottom = 5.dp),
                            text = "Tentang Saya",
                            fontSize = 15.sp,
                            style = Typography.caption
                        )

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth()
                                .height(125.dp),
                            shape = RoundedCornerShape(5.dp),
                            maxLines = 5,
                            value = about.value,
                            onValueChange = {
                                about.value = it
                            },
                            placeholder = { Text(text = "Saya suka pisang, pisang bapak budi sangat besar") },
                        )
                    }
                }
            }

            // Button Section
            Column( modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BottomLanding(label = stringResource(id = R.string.save)) {
                    navController.popBackStack()
                    navController.navigate(TandatanganioScreen.Profile.route)
                }
            }
        }
    }
}


//@Preview
//@Composable
//fun EditProfileScreenPreview() {
//    val mockuser = User(R.drawable.avatar, "Asep Konco")
//    EditProfileScreen(mockuser)
//}