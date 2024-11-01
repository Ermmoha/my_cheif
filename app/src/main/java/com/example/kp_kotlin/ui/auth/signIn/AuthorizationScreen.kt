package com.example.kp_kotlin.ui.auth.signIn

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
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
import com.example.kp_kotlin.ui.navigation.NavigationDestination

object AuthorizationDestination : NavigationDestination {
    override val title = "Авторизация"
    override val route = "auth"
}

@Composable
fun AuthorizationScreen(
    navigateToHome: () -> Unit,
    navigateToReg: () -> Unit
) {
    // Состояния для хранения пользовательского ввода
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize() // Занимает весь экран
    ) {
        // Фоновое изображение
        Image(
            painter = painterResource(id = R.drawable.fon_reg), // Ваше изображение в папке drawable
            contentDescription = null, // Описание для доступности (null, если не нужно)
            modifier = Modifier.fillMaxSize(), // Изображение заполняет весь контейнер
            contentScale = ContentScale.Crop // Обрезка изображения, чтобы оно заполнило контейнер
        )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black.copy(alpha = 0.5f))
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center // Центрирование по вертикали
    )
    {
        Text(text = "Добро пожаловать",
            style = MaterialTheme.typography.headlineLarge,
            fontFamily = FontFamily(Font(R.font.two)),
            color = colorResource(id = R.color.white),
            fontWeight = FontWeight.ExtraBold,



        )
        Image(
            painter = painterResource(id = R.drawable.reg_screen), // Замените на ваш ресурс
            contentDescription = "My image description", // Описание изображения для доступности
            modifier = Modifier
                .fillMaxWidth() // Заполнение ширины
                .height(300.dp), // Высота изображения
            contentScale = ContentScale.FillHeight // Как изображение будет обрезано
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
                Icon(imageVector = Icons.Default.Person,
                    contentDescription = "Login",
                    tint = colorResource(R.color.gold)
                )
            },
            singleLine = true,
            value = name,
            onValueChange = { name = it },
            label = { Text("Логин") },
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
                    name.isEmpty() -> errorMessage = "Name is required"
                    password.isEmpty() -> errorMessage = "Password is required"
                    else -> navigateToHome()
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

@Preview(showBackground = true)
@Composable
fun AuthorizationScreenPreview() {
    AuthorizationScreen(
        navigateToReg = {},
        navigateToHome = {}
    )
}