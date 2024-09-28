package com.ua.hotel_searcher_app.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ua.hotel_searcher_app.R

@Composable
fun Profile() {
    val viewModel = hiltViewModel<ProfileViewModel>()
    val userName by viewModel.userName.collectAsState()

    var userNameLocal by remember {
        mutableStateOf("")
    }

    var isToggled by remember { mutableStateOf(false) }

    //TODO extract methods
    if (userName.isEmpty()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "Elegir nombre de usuario",
                fontWeight = FontWeight.Bold,
            )

            TextField(
                value = userNameLocal,
                onValueChange = { userNameLocal = it },
                label = {
                    Text(text = "Username")
                },
            )

            Button(
                onClick = { viewModel.saveToDataStore(userNameLocal) },
                colors = ButtonDefaults.buttonColors(
                    //TODO extract colours
                    containerColor = Color(0xff349aa6),
                    contentColor = Color.Black
                )
            ) {
                Text(text = "save")
            }
        }
    } else {

        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    contentDescription = "Profile Icon",
                    modifier = Modifier.size(150.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))

                Text(
                    text = viewModel.userName.value,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Dark Mode",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.weight(1f)
                )

                Switch(
                    checked = isToggled,
                    onCheckedChange = { isToggled = it },
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = MaterialTheme.colorScheme.primary,
                        uncheckedThumbColor = MaterialTheme.colorScheme.onBackground
                    )
                )
            }
        }
    }
}

@Composable
@Preview
fun PreviewProfile() {
    Profile()
}

