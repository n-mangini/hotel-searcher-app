package com.ua.innVista.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ua.innVista.R

@Composable
fun Profile() {
    val viewModel = hiltViewModel<ProfileViewModel>()
    val userName by viewModel.userName.collectAsState()

    if (userName.isEmpty()) {
        SetUsername(
            onSave = { viewModel.saveToDataStore(it) }
        )
    } else {
        UserProfile(
            userName = userName
        )
    }
}

@Composable
fun SetUsername(
    onSave: (String) -> Unit
) {
    var userNameLocal by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(R.string.ask_user_name),
            fontWeight = FontWeight.Bold,
        )

        TextField(
            value = userNameLocal,
            onValueChange = { userNameLocal = it },
            label = {
                Text(text = stringResource(R.string.username))
            },
        )

        Button(
            onClick = { onSave(userNameLocal) },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.appBlue),
                contentColor = colorResource(R.color.black)
            )
        ) {
            Text(text = stringResource(R.string.save))
        }
    }
}

@Composable
fun UserProfile(userName: String) {
    var isToggled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Person,
                contentDescription = stringResource(R.string.profile_icon),
                modifier = Modifier.size(150.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = userName,
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
                text = stringResource(R.string.dark_mode),
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.weight(1f)
            )

            Switch(
                checked = isToggled,
                onCheckedChange = { isToggled = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = colorResource(R.color.appBlue),
                    uncheckedThumbColor = colorResource(R.color.white),
                    checkedTrackColor = colorResource(R.color.appBlueLight),
                    uncheckedTrackColor = colorResource(R.color.gray)
                )
            )
        }
    }
}


@Composable
@Preview
fun PreviewProfile() {
    UserProfile(userName = "Marcelo")
}

@Composable
@Preview
fun PreviewSetUsername() {
    SetUsername {}
}
