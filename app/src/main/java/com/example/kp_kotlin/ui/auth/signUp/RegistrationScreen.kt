package com.example.kp_kotlin.ui.auth.signUp
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.* // Для разметки и отступов
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.* // Материальные компоненты
import androidx.compose.runtime.* // Состояния
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

object RegistrationDestination : NavigationDestination {
    override val title = "Регистрация"
    override val route = "reg"
}

    @Composable
    fun RegistrationScreen(
        navigateToAut: () -> Unit
    ) {
        // Состояния для хранения пользовательского ввода
        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
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
            ) {

                Text(
                    text = "Регистрация",
                    fontSize = 30.sp,
                    fontFamily = FontFamily(Font(R.font.two)),
                    color = colorResource(id = R.color.white),
                    fontWeight = FontWeight.ExtraBold


                )

                Spacer(modifier = Modifier.height(16.dp))

                TextField(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Login",
                            tint = colorResource(R.color.gold)
                        )
                    },
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Логин") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email",
                            tint = colorResource(R.color.purple_500)
                        )
                    },
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email
                    ),
                    modifier = Modifier.fillMaxWidth(),

                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Lock",
                            tint = colorResource(R.color.dark_red)
                        )
                    },
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Пароль") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Password
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation() // Преобразование для пароля
                )

                Spacer(modifier = Modifier.height(8.dp))

                TextField(
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Lock",
                            tint = colorResource(R.color.dark_red)
                        )
                    },
                    value = confirmPassword,
                    onValueChange = { confirmPassword = it },
                    label = { Text("Повторите пароль") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (errorMessage.isNotEmpty()) {
                    Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
                    Spacer(modifier = Modifier.height(8.dp))
                }

                Button(
                    onClick = {
                        errorMessage = ""
                        when {
                            name.isEmpty() -> errorMessage = "Name is required"
                            email.isEmpty() -> errorMessage = "Email is required"
                            !isValidEmail(email) -> errorMessage =
                                "Invalid email format" // Проверка email
                            password.isEmpty() -> errorMessage = "Password is required"
                            password != confirmPassword -> errorMessage = "Passwords do not match"
                            else -> navigateToAut()
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = colorResource(id = R.color.dark_green))
                ) {
                    Text("Зарегистрироваться")
                }
                TextButton(onClick = { navigateToAut() }) {
                    Text(
                        text = "У меня уже есть аккаунт",
                        color = colorResource(R.color.purple_200)
                    )

                }
            }
        }
    }

    // Функция для проверки валидности email
    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})$".toRegex()
        return emailRegex.matches(email)
    }


    @Preview(showBackground = true)
    @Composable
    fun RegistrationScreenPreview() {
        RegistrationScreen({})
    }