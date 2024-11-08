package com.myTest.authTest

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.kp_kotlin.ui.auth.signIn.AuthorizationScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AuthorizationScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testLoginWithCorrectData() {
        composeTestRule.setContent {
            AuthorizationScreen(
                navigateToReg = {},
                navigateToHome = {}
            )
        }

        val emailField = composeTestRule.onNodeWithText("Email")
        val passwordField = composeTestRule.onNodeWithText("Пароль")
        val loginButton = composeTestRule.onNodeWithText("Войти")

        emailField.performTextInput("valid@test.com")
        passwordField.performTextInput("password123")

        loginButton.performClick()

    }

    @Test
    fun testLoginWithEmptyEmail() {
        composeTestRule.setContent {
            AuthorizationScreen(
                navigateToReg = {},
                navigateToHome = {}
            )
        }

        val emailField = composeTestRule.onNodeWithText("Email")
        val passwordField = composeTestRule.onNodeWithText("Пароль")
        val loginButton = composeTestRule.onNodeWithText("Войти")

        emailField.performTextInput("")
        passwordField.performTextInput("password123")

        loginButton.performClick()

        val errorMessage = composeTestRule.onNodeWithText("Email не может быть пустым")
        errorMessage.assertIsDisplayed()
    }

    @Test
    fun testLoginWithEmptyPassword() {
        composeTestRule.setContent {
            AuthorizationScreen(
                navigateToReg = {},
                navigateToHome = {}
            )
        }

        val emailField = composeTestRule.onNodeWithText("Email")
        val passwordField = composeTestRule.onNodeWithText("Пароль")
        val loginButton = composeTestRule.onNodeWithText("Войти")

        emailField.performTextInput("valid@test.com")
        passwordField.performTextInput("")

        loginButton.performClick()

        val errorMessage = composeTestRule.onNodeWithText("Пароль не может быть пустым")
        errorMessage.assertIsDisplayed()
    }

    @Test
    fun testLoginWithInvalidEmailFormat() {
        composeTestRule.setContent {
            AuthorizationScreen(
                navigateToReg = {},
                navigateToHome = {}
            )
        }

        val emailField = composeTestRule.onNodeWithText("Email")
        val passwordField = composeTestRule.onNodeWithText("Пароль")
        val loginButton = composeTestRule.onNodeWithText("Войти")

        emailField.performTextInput("invalidEmail")
        passwordField.performTextInput("password123")

        loginButton.performClick()

        val errorMessage = composeTestRule.onNodeWithText("Неверный формат Email")
        errorMessage.assertIsDisplayed()
    }

    @Test
    fun testLoginWithIncorrectData() {
        composeTestRule.setContent {
            AuthorizationScreen(
                navigateToReg = {},
                navigateToHome = {}
            )
        }

        val emailField = composeTestRule.onNodeWithText("Email")
        val passwordField = composeTestRule.onNodeWithText("Пароль")
        val loginButton = composeTestRule.onNodeWithText("Войти")

        emailField.performTextInput("valid@test.com")
        passwordField.performTextInput("wrongPassword")

        loginButton.performClick()

    }
}
