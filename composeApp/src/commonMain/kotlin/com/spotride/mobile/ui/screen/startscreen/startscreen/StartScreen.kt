package com.spotride.mobile.ui.screen.startscreen.startscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.spotride.mobile.ui.screen.startscreen.loginscreen.LoginScreen
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

@Composable
fun StartScreen() {
    val screenController = koinInject<ScreenController>()
    val coroutineScope = rememberCoroutineScope()

    val controller = remember { screenController }
    val screenState by controller.state.collectAsState()

    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = inputText,
            onValueChange = {
                if (it.all { char -> char.isDigit() } || it.isEmpty()) {
                    inputText = it
                }
            },
            label = { Text("User ID") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            isError = screenState is State.Error,
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                if (screenState is State.Error) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Error",
                        tint = Color.Red
                    )
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (val state = screenState) {
            is State.NoData -> {
                Text(text = "Enter User ID and press Get User", color = Color.Gray)
            }
            is State.Loading -> {
                Text(text = "Loading...", color = Color.Gray)
            }
            is State.Error -> {
                Text(text = "Error: ${state.message}", color = Color.Red)
            }
            is State.Success -> {
                Text(
                    text = """
                        User: ${state.user.id}
                        Name: ${state.user.username}
                        LastName: ${state.user.lastName}
                        Email: ${state.user.lastName}
                    """.trimIndent(),
                    color = Color.Green
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        GetUserButton(
            onClick = {
                coroutineScope.launch {
                    controller.getUserById(inputText.toInt())
                }
            },
            isEnabled = inputText.isNotEmpty()
        )
    }
}

@Composable
fun GetUserButton(onClick: () -> Unit, isEnabled: Boolean) {
    Button(
        onClick = { onClick() },
        enabled = isEnabled
    ) {
        Text("Get User")
    }
}

@Preview
@Composable
fun StartScreenPreview() {
    LoginScreen()
//    StartScreen()
}
