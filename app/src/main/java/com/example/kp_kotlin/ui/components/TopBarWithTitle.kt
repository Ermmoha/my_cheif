package com.example.kp_kotlin.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kp_kotlin.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithTitle(
    title: String,
    canNavigateBack: Boolean = false,
    navigateToBack: () -> Unit,
    onLike: Boolean,
    cardLike: Boolean,
    isCreate: Boolean,
    onLikeClick: () -> Unit,
    onDeleteClick: () -> Unit,
    navigateToEdit: (Int) -> Unit,
    id: Int
) {
    TopAppBar(
        title = {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                if (canNavigateBack) {
                    IconButton(
                        onClick = navigateToBack,
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            tint = colorResource(id = R.color.white),
                            contentDescription = "Back",
                            modifier = Modifier.size(25.dp)
                        )
                    }
                    if (onLike && !isCreate) {
                        IconButton(
                            onClick = onLikeClick,
                            modifier = Modifier.align(Alignment.CenterEnd)
                        ) {
                            Icon(
                                imageVector = if (cardLike)
                                    Icons.Filled.Favorite
                                else
                                    Icons.Outlined.FavoriteBorder,
                                contentDescription = null,
                                tint = colorResource(id = R.color.white),
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                    if (isCreate) {
                        IconButton(
                            onClick = { navigateToBack()
                                      onDeleteClick()
                                      },
                            modifier = Modifier
                                .align(Alignment.CenterEnd)

                        ) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Удаление рецепта",
                                tint = colorResource(id = R.color.white),
                                modifier = Modifier.size(30.dp)
                            )
                        }
                    }
                }
                if (isCreate) {
                    IconButton(
                        onClick = { navigateToEdit(id)},
                        modifier = Modifier
                            .padding(45.dp)
                            .align(Alignment.CenterEnd)

                    ) {
                        Icon(
                            imageVector = Icons.Default.Create,
                            contentDescription = "Редактирование рецепта",
                            tint = colorResource(id = R.color.white),
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }

                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    fontSize = 28.sp,
                    fontFamily = FontFamily(Font(R.font.four)),
                    color = colorResource(id = R.color.white),
                    fontWeight = FontWeight.ExtraBold,
                    maxLines = 1,
                    modifier = Modifier.align(Alignment.Center))
                }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = colorResource(id = R.color.dark_green))
    )
}

//@Preview
//@Composable
//fun TopBarPreview() {
//    TopBarWithTitle(
//        title = "GHBDTN",
//        canLike = true,
//        canNavigateBack = true,
//        navigateToBack = {},
//
//    )
//}