package com.example.kp_kotlin

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.kp_kotlin.ui.navigation.ChiefNavGraph

@Composable
fun ChiefApp(
    navController: NavHostController = rememberNavController()
) {
    ChiefNavGraph(
        navController = navController
    )
}
