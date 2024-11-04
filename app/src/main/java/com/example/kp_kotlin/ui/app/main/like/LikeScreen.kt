package com.example.kp_kotlin.ui.app.main.like

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.kp_kotlin.R
import com.example.kp_kotlin.db.domain.model.Recipe
import com.example.kp_kotlin.ui.navigation.NavigationDestination

object LikeDestination : NavigationDestination {
    override val title = "Избранное"
    override val route = "like"
}


@Composable
fun LikeScreen(
    viewModel: LikeViewModel,
    navigateToCard: (Int) -> Unit,
    navigateToEdit: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CardScreen(
            navigateToCard = navigateToCard,
            navigateToCreation = navigateToEdit,
            viewModel = viewModel
            )


    }
}

@Composable
fun CardRow(
    recipeList: List<Recipe>,
    title: String,
    showUploadItem: Boolean,
    navigateToCard: (Int) -> Unit,
    navigateToCreation: (Int) -> Unit
) {
    Column {
        Text(
            text = title,
            fontSize = 30.sp,
            fontFamily = FontFamily(Font(R.font.six)),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )

        LazyRow(
            modifier = Modifier.padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            if (showUploadItem) {
                item {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        shadowElevation = 4.dp,
                        color = Color.White,
                        modifier = Modifier
                            .width(230.dp)
                            .height(150.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.padding(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .clickable { navigateToCreation(0) }, // Вызов функции создания рецепта
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Icon(
                                        Icons.Default.Add,
                                        contentDescription = "Создать рецепт",
                                        tint = colorResource(id = R.color.purple_500)
                                    )
                                    Text(
                                        text = "Добавьте рецепт",
                                        color = colorResource(id = R.color.purple_500)
                                    )
                                }
                            }
                        }
                    }
                }
            }

            items(recipeList) { recipe ->
                CardItem(
                    title = recipe.firstName,
                    navigateToCard = navigateToCard,
                    id = recipe.id,
                    image  = rememberAsyncImagePainter(model = recipe.imageUri),

                    )
            }
        }
    }
}

@Composable
fun CardItem(
    image: AsyncImagePainter,
    id: Int,
    title: String,
    navigateToCard: (Int) -> Unit
    ) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        shadowElevation = 4.dp,
        color = Color.White,
        modifier = Modifier
            .width(200.dp)
            .height(150.dp)
            .clickable { navigateToCard(id) }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = image,
                contentDescription = null,
                contentScale = ContentScale.Crop,
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = 0.2f),
                                Color.Black.copy(alpha = 0.4f),
                                Color.Black.copy(alpha = 0.8f)
                            )
                        )
                    )
            )
            Text(
                text = title,
                color = Color.White,
                fontSize = 23.sp,
                fontFamily = FontFamily(Font(R.font.six)),
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.BottomCenter),
                textAlign = TextAlign.Center
            )
        }
        }
    }


@Composable
fun CardScreen(
    viewModel: LikeViewModel,
    navigateToCard: (Int) -> Unit,
    navigateToCreation: (Int) -> Unit
) {
    val state by viewModel.state.collectAsState()
    Column {
        CardRow(
            recipeList = state.recipeCreateList,
            title = "Ваши рецепты",
            showUploadItem = true,
            navigateToCard = navigateToCard,
            navigateToCreation = navigateToCreation // Передаем функцию для создания рецепта
        )

        CardRow(
            recipeList = state.recipeFavoriteList,
            title = "Любимое",
            showUploadItem = false,
            navigateToCard = navigateToCard, // Передаем функцию для карточек
            navigateToCreation = navigateToCreation // Не используется в этой секции
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewLikeScreen() {
//    LikeScreen(
//        navigateToCreation = {},
//        navigateToCard = {},
//        viewModel = LikeViewModel({})
//    )
//}
