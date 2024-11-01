package com.example.kp_kotlin.ui.app.other.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kp_kotlin.R
import com.example.kp_kotlin.ui.navigation.NavigationDestination

object AboutDestination : NavigationDestination {
    override val title = "О нас"
    override val route = "About"
}

@Composable
fun AboutScreen() {

        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp, 0.dp)
        )
        {
            Description()
        }
    }

@Composable
fun Description(){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        item {
            Text(
                text = "О проекте",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.six)),
                modifier = Modifier.padding(bottom = 6.dp)
            )
        }
        item {
            Text(
                text = "Мой шеф повар - это удобное мобильное приложение, созданное для тех, кто любит готовить или хочет научиться создавать вкусные и оригинальные блюда. Это ваш личный кулинарный помощник, который собрал лучшие рецепты в одном месте. Независимо от того, новичок вы на кухне или опытный повар, приложение поможет вам с легкостью находить рецепты, планировать меню и воплощать самые смелые кулинарные идеи.",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.six)),
                modifier = Modifier.padding(bottom = 6.dp)
            )
        }
        item{
            Text(
                text = "Возможности приложения:",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.six)),
                modifier = Modifier.padding(bottom = 8.dp)
            )}

        val features = listOf(
            "Большая база рецептов: от простых завтраков до сложных блюд высокой кухни — все рецепты доступны в одном приложении.",
            "Удобная навигация: рецепты разделены на категории, такие как 'Завтраки', 'Первые блюда', 'Вторые блюда', 'Десерты' и другие, что делает поиск нужного блюда быстрым и простым.",
            "Красивые фотографии блюд: каждая карточка рецепта сопровождается яркими изображениями, которые вдохновляют на готовку.",
            "Избранные рецепты: сохраняйте свои любимые рецепты в избранное для быстрого доступа.",
            "Готовьте по шагам: пошаговые инструкции помогут вам точно следовать рецепту и не допустить ошибок.",
        )

        features.forEach { feature ->
            item {  Text(
                text = "• $feature",
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.six)),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            }
        }

    }
}



@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
    AboutScreen()
}
