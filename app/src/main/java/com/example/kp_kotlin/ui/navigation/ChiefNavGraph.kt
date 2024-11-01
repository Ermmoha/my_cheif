package com.example.kp_kotlin.ui.navigation

import ProfileDestination
import ProfileScreen
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.kp_kotlin.ui.app.main.catalogue.CatalogueDestination
import com.example.kp_kotlin.ui.app.main.catalogue.CatalogueScreen
import com.example.kp_kotlin.ui.app.main.home.HomeDestination
import com.example.kp_kotlin.ui.app.main.home.HomeScreen
import com.example.kp_kotlin.ui.app.main.home.HomeViewModel
import com.example.kp_kotlin.ui.app.main.like.LikeDestination
import com.example.kp_kotlin.ui.app.main.like.LikeScreen
import com.example.kp_kotlin.ui.app.main.like.LikeViewModel
import com.example.kp_kotlin.ui.app.other.about.AboutDestination
import com.example.kp_kotlin.ui.app.other.about.AboutScreen
import com.example.kp_kotlin.ui.app.other.card.CardDestination
import com.example.kp_kotlin.ui.app.other.card.CardPreview
import com.example.kp_kotlin.ui.app.other.card.CardPreviewViewModel
import com.example.kp_kotlin.ui.app.other.category.CategoryDestination
import com.example.kp_kotlin.ui.app.other.category.CategoryDestination.title
import com.example.kp_kotlin.ui.app.other.category.CategoryScreen
import com.example.kp_kotlin.ui.app.other.category.CategoryViewModel
import com.example.kp_kotlin.ui.app.other.creation.CreationDestination
import com.example.kp_kotlin.ui.app.other.creation.RecipeCreationScreen
import com.example.kp_kotlin.ui.app.other.creation.RecipeCreationViewModel
import com.example.kp_kotlin.ui.app.other.edit.EditDestination
import com.example.kp_kotlin.ui.app.other.edit.RecipeEditScreen
import com.example.kp_kotlin.ui.app.other.edit.RecipeEditViewModel
import com.example.kp_kotlin.ui.auth.signIn.AuthorizationDestination
import com.example.kp_kotlin.ui.auth.signIn.AuthorizationScreen
import com.example.kp_kotlin.ui.auth.signUp.RegistrationDestination
import com.example.kp_kotlin.ui.auth.signUp.RegistrationScreen
import com.example.kp_kotlin.ui.components.MyBottomAppBar
import com.example.kp_kotlin.ui.components.TopBarWithTitle

@Composable
fun ChiefNavGraph(
    navController: NavHostController
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        topBar = {
            if (currentRoute != RegistrationDestination.route && currentRoute != AuthorizationDestination.route && currentRoute != CardDestination.route)
            {
                TopBarWithTitle(
                    title = when (currentRoute) {
                        HomeDestination.route -> "Главная"
                        LikeDestination.route -> "Избранное"
                        CatalogueDestination.route -> "Категории"
                        ProfileDestination.route -> "Профиль"
                        AboutDestination.route -> "О нас"
                        CategoryDestination.route -> title
                        else -> "Мой шеф-повар"
                    },
                    canNavigateBack = when (currentRoute) {
                        HomeDestination.route,
                        LikeDestination.route,
                        CatalogueDestination.route,
                        ProfileDestination.route -> false
                        else -> true
                    },
                    onLike = false,
                    cardLike = true,
                    onLikeClick = {},
                    onDeleteClick = {},
                    navigateToEdit = {},
                    id = 0,
                    isCreate = false,
                    navigateToBack = { navController.popBackStack() },
                )
            }
        },
        bottomBar = {
            if (currentRoute == HomeDestination.route || currentRoute == CatalogueDestination.route || currentRoute == LikeDestination.route || currentRoute == ProfileDestination.route) {
                MyBottomAppBar(
                    navigateToLike = { navController.navigate(LikeDestination.route) },
                    navigateToCatalogue = { navController.navigate(CatalogueDestination.route) },
                    navigateToProfile = { navController.navigate(ProfileDestination.route) },
                    navigateToHome = { navController.navigate(HomeDestination.route) }
                )
            }
        }
    )
     { contentPadding ->
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = Modifier.padding(contentPadding)
    ) {
        composable(route = CatalogueDestination.route,) {
            CatalogueScreen(
                navigateToCategory = {
                    navController.navigate(CategoryDestination.route)
                    CategoryDestination.title = it

                }
            )
        }
        composable(route = AuthorizationDestination.route) {
            AuthorizationScreen(
                navigateToHome = { navController.navigate(HomeDestination.route) },
                navigateToReg = { navController.navigate(RegistrationDestination.route) }
            )
        }
        composable(route = RegistrationDestination.route) {
            RegistrationScreen(
                navigateToAut = { navController.navigate(AuthorizationDestination.route) })
        }
        composable(route = HomeDestination.route) {
            val homeViewModel = hiltViewModel<HomeViewModel>()
            HomeScreen(
                viewModel = homeViewModel,
                navigateToCard = {
                    navController.navigate(CardDestination.route)
                    CardDestination.cardId = it
                }
            )
        }
        composable(route = ProfileDestination.route) {
            ProfileScreen(
                navigateToAbout = {navController.navigate(AboutDestination.route)},
                navigateToReg = {navController.navigate(RegistrationDestination.route)},
                navigateToCreation = {navController.navigate(CreationDestination.route)}

            )
        }
        composable(route = LikeDestination.route) {
            val likeViewModel = hiltViewModel<LikeViewModel>()
            LikeScreen(
                viewModel = likeViewModel,
                navigateToCreation = {
                    navController.navigate(EditDestination.route)
                    EditDestination.cardId = 0
                                     },
                navigateToCard = {
                    navController.navigate(CardDestination.route)
                    CardDestination.cardId = it
                }
            )
        }
        composable(route = AboutDestination.route) {
            AboutScreen()
        }
        composable(route = CardDestination.route) {
            val viewModel = hiltViewModel<CardPreviewViewModel>()
            CardPreview(
                viewModel = viewModel,
                navigateToBack = { navController.popBackStack() },
                navigateToEdit = {
                    (navController.navigate(EditDestination.route))
                    EditDestination.cardId = it
                }
            )
        }

        composable(route = CreationDestination.route) {
            val viewModel = hiltViewModel<RecipeCreationViewModel>()
            RecipeCreationScreen(
                viewModel = viewModel,
                navigateBack = {navController.popBackStack()}
            )
        }
        composable(route = CategoryDestination.route) {
            val viewModel = hiltViewModel<CategoryViewModel>()
            CategoryScreen(
                viewModel =viewModel,
                navigateToCard = {
                    navController.navigate(CardDestination.route)
                    CardDestination.cardId = it
                }
            )
        }
        composable(route = EditDestination.route) {
            val viewModel = hiltViewModel<RecipeEditViewModel>()
            RecipeEditScreen(
                viewModel = viewModel,
                navigateBack = {navController.popBackStack()}
                )
        }
    }
    }
}