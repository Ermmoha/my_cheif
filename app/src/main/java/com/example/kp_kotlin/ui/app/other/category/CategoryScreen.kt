package com.example.kp_kotlin.ui.app.other.category

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter
import com.example.kp_kotlin.ui.app.main.home.CardView
import com.example.kp_kotlin.ui.navigation.NavigationDestination

object CategoryDestination : NavigationDestination {
    override var title = "Категория"
    override val route = "category"
}

@Composable
fun CategoryScreen(

    viewModel: CategoryViewModel,
    navigateToCard: (Int) -> Unit
    ){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        CardsList(
            navigateToCard = navigateToCard,
            viewModel = viewModel
        )
    }
}
@Composable
fun CardsList(
    viewModel: CategoryViewModel,
    navigateToCard: (Int) -> Unit
){
    val state by viewModel.state.collectAsState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .fillMaxSize()
    ) {
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


//@Preview(showBackground = true)
//@Composable
//fun AllCardPreview(){
//    CategoryScreen({})
//}