package com.example.kp_kotlin.ui.auth.signUp
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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.AlertDialog
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
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.auth

object RegistrationDestination : NavigationDestination {
    override val title = "Регистрация"
    override val route = "reg"
}

    @Composable
    fun RegistrationScreen(
        navigateToAut: () -> Unit
    ) {
        val auth = Firebase.auth
        var showDialog by remember { mutableStateOf(false) }
        var name by remember { mutableStateOf("") }
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmPassword by remember { mutableStateOf("") }
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
                    visualTransformation = PasswordVisualTransformation()
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
                if (showDialog) {
                    AlertDialog(
                        onDismissRequest = { showDialog = false },
                        title = { Text("Регистрация успешна!") },
                        text = { Text("Вы успешно зарегистрировались.") },
                        confirmButton = {
                            TextButton(onClick = {
                                showDialog = false
                                navigateToAut()
                            }) {
                                Text("OK")
                            }
                        })
                }
                Button(
                    onClick = {
                        errorMessage = ""
                        when {
                            name.isEmpty() -> errorMessage = "Имя не может быть пустым"
                            email.isEmpty() -> errorMessage = "Email не может быть пустым"
                            password.length < 6 -> errorMessage = "Длина пароля должна быть не менее 6 символов"
                            !isValidEmail(email) -> errorMessage =
                                "Неверный формат Email "
                            password.isEmpty() -> errorMessage = "Пароль не может быть пустым"
                            password != confirmPassword -> errorMessage = "Пароли различаются"
                            else -> {
                                signUp(
                                    auth = auth,
                                    email = email,
                                    name = name,
                                    password = password,
                                    onSuccess = {
                                        showDialog = true
                                    },
                                    onFailure = { error -> errorMessage = error }
                                )
                            }
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

private fun signUp(
    auth: FirebaseAuth,
    email: String,
    name: String,
    password: String,
    onSuccess: () -> Unit,
    onFailure: (String) -> Unit,
){
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(name)
                    .build()
                user?.updateProfile(profileUpdates)
                    ?.addOnCompleteListener { profileTask ->
                        if (profileTask.isSuccessful) {
                            Log.d("MyLog", "Регистрация прошла успешно!")
                            onSuccess()
                        }
                    }
            } else {
                val errorMessage = "Не удалось создать аккаунт"
                Log.d("MyLog", "Регистрация не удалась")
                onFailure(errorMessage)
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