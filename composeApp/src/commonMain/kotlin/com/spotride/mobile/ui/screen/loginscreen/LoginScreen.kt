package com.spotride.mobile.ui.screen.loginscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.spotride.mobile.ui.screen.registration.RegistrationScreen
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject

/**
 * Screen for User login.
 */
@Composable
fun LoginScreen() {
    val emailPattern = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")
    val registrationScreenController = koinInject<LoginScreenController>()
    val coroutineScope = rememberCoroutineScope()

    val controller = remember { registrationScreenController }

    var inputEmail by remember { mutableStateOf("") }
    var inputPassword by remember { mutableStateOf("") }
    var isEmailValid by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top Section
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Log into Spotride",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding(top = 200.dp)
                    .padding(bottom = 32.dp)
            )

            Text(
                modifier = Modifier.align(Alignment.Start),
                textAlign = TextAlign.Left,
                text = "User Email"
            )
            OutlinedTextField(
                value = inputEmail,
                onValueChange = {
                    inputEmail = it
                    isEmailValid = it.matches(emailPattern) || it.isEmpty()
                },
                label = { Text("Email") },
                isError = !isEmailValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
            )

            Text(
                modifier = Modifier.align(Alignment.Start),
                textAlign = TextAlign.Left,
                text = "Password"
            )
            OutlinedTextField(
                value = inputPassword,
                onValueChange = {
                    if (it.all { char -> char.isLetter() } || it.isEmpty()) {
                        inputPassword = it
                    }
                },
                label = { Text("password") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
            )

            Button(
                onClick = {
                    coroutineScope.launch {
                        controller.doLoginUser(inputEmail, inputPassword)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Login")
            }

            Row {
                Text(
                    text = "Don't have an account? ",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Text(
                    text = "Sign up",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.clickable { /* TODO: sign up */ }
                )
            }
        }

        // Bottom Section
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Spotride App © ${getYear()}",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "English",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Language Selection",
                        tint = Color.Gray
                    )
                }
            }
        }
    }
}

fun getYear() : String {
    val currentMoment = Clock.System.now()
    val toLocalDateTime = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())
    return toLocalDateTime.year.toString()
}

@Preview
@Composable
fun RegisterScreenPreview() {
    RegistrationScreen()
}
