package com.example.kp_kotlin.ui.app.main.catalogue

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kp_kotlin.R
import com.example.kp_kotlin.ui.model.Category
import com.example.kp_kotlin.ui.navigation.NavigationDestination

object CatalogueDestination : NavigationDestination{
    override val title = "Каталог"
    override val route = "catalogue"
}

@Composable
fun CatalogueScreen(
    navigateToCategory: (String) -> Unit
){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {

        CategoryViews(navigateToCategory = navigateToCategory)

    }
}


@Composable
fun CategoryViews(
    navigateToCategory: (String) -> Unit
){
    val listText = listOf<Category>(
        Category("Завтраки", R.drawable.kat6),
        Category("Первые блюда", R.drawable.kat4),
        Category("Вторые блюда", R.drawable.kat2),
        Category("Салаты",R.drawable.kat3),
        Category("Десерты",R.drawable.kat1),
        Category("Напитки",R.drawable.kat5)
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()

        ) {
            items(listText){category ->
                CategoryView(category, navigateToCategory = navigateToCategory)
            }
        }
    }
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryView(
        category: Category,
        navigateToCategory: (String) -> Unit
    ){
        Card(
            onClick = { navigateToCategory(category.title)},
            modifier = Modifier
                .size(400.dp, 145.dp)
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(

                    painter = painterResource(id = category.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
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
                    text = category.title,
                    color = Color.White,
                    fontSize = 30.sp,
                    fontFamily = FontFamily(Font(R.font.six)),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(8.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
@Preview(showBackground = true)
@Composable
fun PreviewProfileScreen() {
    CatalogueScreen({})
}