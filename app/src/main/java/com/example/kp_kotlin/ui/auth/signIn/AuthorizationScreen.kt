package com.example.kp_kotlin.ui.auth.signIn

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kp_kotlin.R
import com.example.kp_kotlin.ui.auth.signUp.isValidEmail
import com.example.kp_kotlin.ui.navigation.NavigationDestination
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

object AuthorizationDestination : NavigationDestination {
    override val title = "Авторизация"
    override val route = "auth"
}

@Composable
fun AuthorizationScreen(
    navigateToHome: () -> Unit,
    navigateToReg: () -> Unit
) {
    val context = LocalContext.current
    val auth = Firebase.auth
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.fon_reg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.5f))
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Text(text = "Добро пожаловать",
            style = MaterialTheme.typography.headlineLarge,
            fontFamily = FontFamily(Font(R.font.two)),
            color = colorResource(id = R.color.white),
            fontWeight = FontWeight.ExtraBold,



        )
        Image(
            painter = painterResource(id = R.drawable.reg_screen),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            contentScale = ContentScale.FillHeight
        )

        Text(
            text = "Мой шеф-повар",
            color = Color.Yellow,
            fontSize = 25.sp,
            fontFamily = FontFamily(Font(R.font.two)),
            )

        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            leadingIcon = {
                Icon(imageVector = Icons.Default.Email,
                    contentDescription = "Email",
                    tint = colorResource(R.color.gold)
                )
            },
            singleLine = true,
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            leadingIcon = {
                Icon(imageVector = Icons.Default.Lock,
                    contentDescription = "Lock",
                    tint = colorResource(R.color.dark_red)
                )
            },
            singleLine = true,
            value = password,
            onValueChange = { password = it },
            label = { Text("Пароль") },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                errorMessage = ""
                when {
                    email.isEmpty() -> errorMessage = "Email не может быть пустым"
                    !isValidEmail(email) -> errorMessage = "Неверный формат Email"
                    password.isEmpty() -> errorMessage = "Пароль не может быть пустым"
                    else -> {
                        signIn(auth, email, password,
                            onSuccess = { navigateToHome() },
                            onFailure = { error -> errorMessage = error }
                        )
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = colorResource(R.color.dark_green)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Войти",
                color = colorResource(R.color.white),
                fontWeight = FontWeight.ExtraBold
                )
        }
        TextButton(onClick = { navigateToReg() })
        {
            Text(
                text = "Нет аккаунта",
                color = colorResource(R.color.purple_200),
                fontSize = 16.sp
                )

        }
    }
}
}

private fun signIn(
    auth: FirebaseAuth,
    email: String,
    password: String,
    onSuccess: () -> Unit,
    onFailure: (String) -> Unit
) {
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Log.d("Log", "Вход выполнен успешно!")
                onSuccess()
            } else {
                val errorMessage = "Неверный логин или пароль"
                Log.d("Log", "Не удалось войти в аккаунт")
                onFailure(errorMessage)
            }
        }
}

@Preview(showBackground = true)
@Composable
fun AuthorizationScreenPreview() {
    AuthorizationScreen(
        navigateToReg = {},
        navigateToHome = {}
    )
}