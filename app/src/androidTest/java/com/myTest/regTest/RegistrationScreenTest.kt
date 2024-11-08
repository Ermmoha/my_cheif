package com.myTest.regTest

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kp_kotlin.ui.auth.signUp.RegistrationScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegistrationScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testRegistrationWithCorrectData() {
        composeTestRule.setContent {
            RegistrationScreen(
                navigateToAut = {},
                navigateToHome = {}
            )
        }

        // Находим UI элементы
        val nameField = composeTestRule.onNodeWithText("Логин")
        val emailField = composeTestRule.onNodeWithText("Email")
        val passwordField = composeTestRule.onNodeWithText("Пароль")
        val confirmPasswordField = composeTestRule.onNodeWithText("Повторите пароль")
        val registerButton = composeTestRule.onNodeWithText("Зарегистрироваться")

        nameField.performTextInput("User")
        emailField.performTextInput("user@test.com")
        passwordField.performTextInput("password123")
        confirmPasswordField.performTextInput("password123")

        registerButton.performClick()

    }

    @Test
    fun testRegistrationWithEmptyName() {
        composeTestRule.setContent {
            RegistrationScreen(navigateToAut = {},
                navigateToHome = {}
            )
        }

        val nameField = composeTestRule.onNodeWithText("Логин")
        val emailField = composeTestRule.onNodeWithText("Email")
        val passwordField = composeTestRule.onNodeWithText("Пароль")
        val confirmPasswordField = composeTestRule.onNodeWithText("Повторите пароль")
        val registerButton = composeTestRule.onNodeWithText("Зарегистрироваться")

        emailField.performTextInput("user@test.com")
        passwordField.performTextInput("password123")
        confirmPasswordField.performTextInput("password123")

        registerButton.performClick()

        val errorMessage = composeTestRule.onNodeWithText("Имя не может быть пустым")
        errorMessage.assertIsDisplayed()
    }

    @Test
    fun testRegistrationWithEmptyEmail() {
        composeTestRule.setContent {
            RegistrationScreen(navigateToAut = {},
                navigateToHome = {}
            )
        }

        val nameField = composeTestRule.onNodeWithText("Логин")
        val emailField = composeTestRule.onNodeWithText("Email")
        val passwordField = composeTestRule.onNodeWithText("Пароль")
        val confirmPasswordField = composeTestRule.onNodeWithText("Повторите пароль")
        val registerButton = composeTestRule.onNodeWithText("Зарегистрироваться")

        nameField.performTextInput("User")
        passwordField.performTextInput("password123")
        confirmPasswordField.performTextInput("password123")

        registerButton.performClick()

        val errorMessage = composeTestRule.onNodeWithText("Email не может быть пустым")
        errorMessage.assertIsDisplayed()
    }

    @Test
    fun testRegistrationWithInvalidEmail() {
        composeTestRule.setContent {
            RegistrationScreen(navigateToAut = {},
                navigateToHome = {}
            )
        }

        val nameField = composeTestRule.onNodeWithText("Логин")
        val emailField = composeTestRule.onNodeWithText("Email")
        val passwordField = composeTestRule.onNodeWithText("Пароль")
        val confirmPasswordField = composeTestRule.onNodeWithText("Повторите пароль")
        val registerButton = composeTestRule.onNodeWithText("Зарегистрироваться")

        nameField.performTextInput("User")
        emailField.performTextInput("invalidEmail")
        passwordField.performTextInput("password123")
        confirmPasswordField.performTextInput("password123")

        registerButton.performClick()

        composeTestRule.onNodeWithText("Неверный формат Email").assertExists()

        val errorMessage = composeTestRule.onNodeWithText("Неверный формат Email")
        errorMessage.assertIsDisplayed()
    }

    @Test
    fun testRegistrationWithPasswordMismatch() {
        composeTestRule.setContent {
            RegistrationScreen(navigateToAut = {},
                navigateToHome = {}
            )
        }

        val nameField = composeTestRule.onNodeWithText("Логин")
        val emailField = composeTestRule.onNodeWithText("Email")
        val passwordField = composeTestRule.onNodeWithText("Пароль")
        val confirmPasswordField = composeTestRule.onNodeWithText("Повторите пароль")
        val registerButton = composeTestRule.onNodeWithText("Зарегистрироваться")

        nameField.performTextInput("User")
        emailField.performTextInput("user@test.com")
        passwordField.performTextInput("password123")
        confirmPasswordField.performTextInput("password456")

        registerButton.performClick()

        val errorMessage = composeTestRule.onNodeWithText("Пароли различаются")
        errorMessage.assertIsDisplayed()
    }

    @Test
    fun testRegistrationWithEmptyPassword() {
        composeTestRule.setContent {
            RegistrationScreen(navigateToAut = {},
                navigateToHome = {}
            )
        }

        val nameField = composeTestRule.onNodeWithText("Логин")
        val emailField = composeTestRule.onNodeWithText("Email")
        val passwordField = composeTestRule.onNodeWithText("Пароль")
        val confirmPasswordField = composeTestRule.onNodeWithText("Повторите пароль")
        val registerButton = composeTestRule.onNodeWithText("Зарегистрироваться")

        nameField.performTextInput("User")
        emailField.performTextInput("user@mail.ru")
        confirmPasswordField.performTextInput("password123")

        registerButton.performClick()

        composeTestRule.onNodeWithText("Пароль не может быть пустым").assertExists()

        val errorMessage = composeTestRule.onNodeWithText("Пароль не может быть пустым")
        errorMessage.assertIsDisplayed()
    }
}


