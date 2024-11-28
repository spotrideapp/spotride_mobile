package com.spotride.mobile.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.spotride.mobile.model.service.UserApiService
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StartScreen(userApiService: UserApiService) {
    val coroutineScope = rememberCoroutineScope()

    var jsonResponse by remember { mutableStateOf("Put User ID and press button") }
    var inputText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Enter User ID:")

        Spacer(modifier = Modifier.height(16.dp))

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
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = jsonResponse.ifEmpty { "No Value" })

        Spacer(modifier = Modifier.height(16.dp))

        GetUserButton(
            onClick = {
                coroutineScope.launch {
                    jsonResponse = try {
                        userApiService.getUserById(inputText.toInt())
                    } catch (e: Exception) {
                        "Error: ${e.message}"
                    }
                }
            },
            isEnabled = inputText.isNotEmpty()
        )
    }
}

@Composable
fun GetUserButton(onClick: () -> Unit, isEnabled: Boolean) {
    Button(onClick = { onClick() }, enabled = isEnabled) {
        Text("Get User")
    }
}

@Preview
@Composable
fun StartScreenPreview() {
    StartScreen(
        userApiService = UserApiService()
    )
}
