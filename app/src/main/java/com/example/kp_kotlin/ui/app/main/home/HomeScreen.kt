package com.example.kp_kotlin.ui.app.main.home
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.kp_kotlin.R
import com.example.kp_kotlin.ui.navigation.NavigationDestination

object HomeDestination : NavigationDestination {
    override val title = "Домик"
    override val route = "home"
}

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navigateToCard: (Int) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        CardViews(
            viewModel = viewModel,
            navigateToCard = navigateToCard
        )
    }
}
@Composable
fun HorizontalCardsPager() {
    val pageCount = 2
    val pagerState = rememberPagerState { Int.MAX_VALUE }

    // Циклический индекс для поддержки бесконечной прокрутки
    val infinitePage: (Int) -> Int = { page ->
        (page % pageCount + pageCount) % pageCount
    }

    HorizontalPager(
        state = pagerState,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp),
        contentPadding = PaddingValues(20.dp,10.dp)
    ) { page ->
        val clickAction: () -> Unit = when (infinitePage(page)) {
            0 -> { {   } }
            1 -> { {  } }
            else -> { {  }
            }
        }
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .height(220.dp),
            shape = RoundedCornerShape(15.dp),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            // Используем Box для наложения текста на изображение
            Box(modifier = Modifier.fillMaxSize()
            ) {
                val image = GetImageForPage(infinitePage(page))

                // Изображение
                Image(
                    painter = image,
                    contentDescription = "Card $page",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Box(modifier = Modifier
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

                // Текст поверх изображения
                Text(
                    text = GetTextForPage(infinitePage(page)),
                    fontFamily = FontFamily(Font(R.font.six)),
                    fontSize = 25.sp,
                    modifier = Modifier
                        .align(Alignment.BottomCenter) // Выравнивание текста
                        .padding(8.dp), // Отступы вокруг текста
                    color = Color.White, // Цвет текста (можно изменить)
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

// Функция для получения текста для каждой страницы
@Composable
fun GetTextForPage(page: Int): String {
    return when (page) {
        0 -> "Топ 5 кулинарных лайфхаков"
        1 -> "Добро пожаловать"

        else -> ""
    }
}

@Composable
fun GetImageForPage(page: Int): Painter {
    return when (page) {
        0 -> painterResource(id = R.drawable.pager1)
        1 -> painterResource(id = R.drawable.fon_reg)
        else -> painterResource(id = R.drawable.reg_screen)
    }
}
@Composable
fun CardViews(
    viewModel: HomeViewModel,
    navigateToCard: (Int) -> Unit
){
    val state by viewModel.state.collectAsState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()

    ) {
        item(span = { GridItemSpan(2) }){ HorizontalCardsPager() }
        item (span = { GridItemSpan(2) }){ Text(
            text = "Рецепты",
            fontFamily = FontFamily(Font(R.font.six)),
            fontSize = 30.sp,
        ) }
        items(state.recipeList){recipe ->
            CardView(
                title = recipe.firstName,
                image = rememberAsyncImagePainter(model = recipe.imageUri),
                navigateToCard = navigateToCard,
                id = recipe.id
                )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardView(
    id: Int,
    image: AsyncImagePainter,
    title: String,
    navigateToCard: (Int) -> Unit
){
    Card(
        onClick = {navigateToCard(id)},
        modifier = Modifier
            .height(150.dp)
            .width(230.dp)
            .padding(3.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                modifier = Modifier,
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
                maxLines = 1,
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


