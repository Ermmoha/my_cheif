package com.example.kp_kotlin.ui.app.other.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.kp_kotlin.R
import com.example.kp_kotlin.ui.components.TopBarWithTitle
import com.example.kp_kotlin.ui.navigation.NavigationDestination

object CardDestination : NavigationDestination {
    var cardId = 0
    override val title = "Рецепт"
    override val route = "Card"
}

@Composable
fun CardPreview(
    viewModel: CardPreviewViewModel,
    navigateToBack: () -> Unit,
    navigateToEdit: (Int) -> Unit
) {
    val state by viewModel.state.collectAsState()
    val isCreate = state.recipe.isCreate

    Scaffold(
        topBar = {
            TopBarWithTitle(
                title = "Рецепт",
                canNavigateBack = true,
                navigateToBack = navigateToBack,
                cardLike = state.recipe.isFavorite,
                onLike = true,
                isCreate = isCreate,
                id = state.recipe.id,
                navigateToEdit = navigateToEdit,
                onDeleteClick = {
                    viewModel.updateState(state.recipe.copy())
                    viewModel.deleteRecipe()
                },
                onLikeClick = {
                    viewModel.updateState(state.recipe.copy(isFavorite = !state.recipe.isFavorite))
                    viewModel.updateRecipe()
                }
        )
    }) { contentPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(contentPadding)
                .padding(12.dp, 6.dp)
        ) {
            item {
                // Фото блюда
                if (state.recipe.imageUri != null) {
                    Image(
                        painter = rememberAsyncImagePainter(model = state.recipe.imageUri),
                        contentDescription = "Фото блюда",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .background(Color.LightGray),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Фото не загружено")
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Название рецепта
            item {
                Text(
                    text = "Название рецепта: ${state.recipe.firstName}",
                    fontFamily = FontFamily(Font(R.font.four)),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            // Описание рецепта
            item {
                Text(
                    text = "Описание:",
                    fontFamily = FontFamily(Font(R.font.four)),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = state.recipe.description,
                    fontFamily = FontFamily(Font(R.font.four)),
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Категория
            item {
                Text(
                    text = "Категория: ${state.recipe.category}",
                    fontFamily = FontFamily(Font(R.font.four)),
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Время приготовления
            item {
                Text(
                    text = "Время приготовления: ${state.recipe.cookingHours} ч. ${state.recipe.cookingMinutes} мин.",
                    fontFamily = FontFamily(Font(R.font.four)),
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Ингредиенты
            item {
                Text(
                    text = "Ингредиенты:",
                    fontFamily = FontFamily(Font(R.font.four)),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                Text(
                    text = state.recipe.ingredients,
                    fontFamily = FontFamily(Font(R.font.four)),
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Шаги приготовления
            item {
                Text(
                    text = "Шаги приготовления:",
                    fontFamily = FontFamily(Font(R.font.four)),
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            item {
                Text(
                    text = state.recipe.steps,
                    fontFamily = FontFamily(Font(R.font.four)),
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun CardPreview(){
//    RecipeDetailsScreen(recipe = Recipe())
//}

