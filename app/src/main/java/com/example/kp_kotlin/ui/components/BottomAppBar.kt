package com.example.kp_kotlin.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.kp_kotlin.R

@Composable
fun MyBottomAppBar(
    navigateToLike: () -> Unit,
    navigateToCatalogue: () -> Unit,
    navigateToProfile: () -> Unit,
    navigateToHome: () -> Unit
){
    var selectedItemIndex by remember { mutableIntStateOf(1) }

    BottomAppBar(
        modifier = Modifier
            .height(65.dp)
            .background(Color.White),
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                ) {
                    NavigationBarItem(
                        selected = selectedItemIndex == 2,
                        onClick = {
                            navigateToCatalogue()
                            selectedItemIndex = 2
                        },
                        icon = {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Category",
                                modifier = Modifier.size(30.dp),
                                tint = if (selectedItemIndex == 2) colorResource(id = R.color.black) else Color.Gray
                            )
                        }
                    )
                NavigationBarItem(
                    selected = selectedItemIndex == 3,
                    onClick = {
                        navigateToLike()
                        selectedItemIndex = 3
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favorit",
                            modifier = Modifier.size(30.dp),
                            tint = if (selectedItemIndex == 3) colorResource(id = R.color.dark_red) else Color.Gray
                        )
                    }
                )
                NavigationBarItem(
                    selected = selectedItemIndex == 1,
                    onClick = {
                        navigateToHome()
                        selectedItemIndex = 1
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Home,
                            contentDescription = "Home",
                            modifier = Modifier.size(30.dp),
                            tint = if (selectedItemIndex == 1) colorResource(id = R.color.dark_green) else Color.Gray
                        )
                    }
                )
                NavigationBarItem(
                    selected = selectedItemIndex == 4,
                    onClick = {
                        navigateToProfile()
                        selectedItemIndex = 4
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Profile",
                            modifier = Modifier.size(30.dp),
                            tint = if (selectedItemIndex == 4) Color.Blue else Color.Gray
                        )
                    }
                )
            }
        }
    )
}
