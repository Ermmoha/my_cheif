package com.example.kp_kotlin.db.data.repository

import com.example.kp_kotlin.R
import com.example.kp_kotlin.db.domain.model.Recipe
import com.example.kp_kotlin.db.domain.repository.RecipeDao
import com.example.kp_kotlin.db.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val dao: RecipeDao
) : RecipeRepository {
    override suspend fun insertNewRecipe(recipe: Recipe) =
        dao.insertNewRecipe(recipe)

    override suspend fun insertInitialRecipes() {
        val recipes = listOf(
            Recipe(
                firstName = "Яичница с помидорами",
                description = "Вкусная и сытная яичница с помидорами и зеленью.",
                category = "Завтраки",
                cookingHours = "0",
                cookingMinutes = "10",
                ingredients = "2 яйца, 1 помидор, 1 столовая ложка масла, соль, перец, зелень по вкусу",
                steps = "1. Разогрейте масло на сковороде. 2. Нарежьте помидор и обжарьте его 2-3 минуты. 3. Разбейте яйца в сковородку, посолите и поперчите. 4. Жарьте до готовности.",
                isCreate = true,
                imageUri = "android.resource://com.example.kp_kotlin/" + R.drawable.recipe1
            ),
            Recipe(
                firstName = "Тосты с авокадо",
                description = "Полезные и вкусные тосты с авокадо и яйцом.",
                category = "Завтраки",
                cookingHours = "0",
                cookingMinutes = "15",
                ingredients = "2 ломтика хлеба, 1 авокадо, 1 яйцо, лимонный сок, соль, перец",
                steps = "1. Поджарьте хлеб. 2. Разомните авокадо с лимонным соком, солью и перцем. 3. Приготовьте яйцо (вареное или жареное). 4. Намажьте авокадо на хлеб и положите яйцо сверху.",
                isCreate = true,
                imageUri = "android.resource://com.example.kp_kotlin/" + R.drawable.kat3
            ),

            // Первые блюда
            Recipe(
                firstName = "Суп-пюре из брокколи",
                description = "Нежный и полезный суп-пюре из брокколи с кремом.",
                category = "Первые блюда",
                cookingHours = "0",
                cookingMinutes = "25",
                ingredients = "300 г брокколи, 1 картофелина, 1 луковица, 200 мл сливок, соль, перец",
                steps = "1. Отварите брокколи, картофель и лук до мягкости. 2. Слейте воду, добавьте сливки, соль и перец. 3. Измельчите в блендере до состояния пюре.",
                isCreate = true,
                imageUri = "android.resource://com.example.kp_kotlin/" + R.drawable.kat3
            ),
            Recipe(
                firstName = "Гречневый суп с грибами",
                description = "Сытный и ароматный суп с гречкой и грибами.",
                category = "Первые блюда",
                cookingHours = "0",
                cookingMinutes = "30",
                ingredients = "100 г гречки, 200 г грибов, 1 луковица, 1 морковь, соль, перец, зелень",
                steps = "1. Обжарьте лук и морковь до золотистого цвета. 2. Добавьте грибы и готовьте еще 5 минут. 3. Добавьте гречку и воду, варите до готовности. 4. Посолите, поперчите и добавьте зелень.",
                isCreate = true,
                imageUri = "android.resource://com.example.kp_kotlin/" + R.drawable.kat3
            ),

            // Вторые блюда
            Recipe(
                firstName = "Куриные грудки в соусе терияки",
                description = "Сочные куриные грудки с ароматным соусом терияки.",
                category = "Вторые блюда",
                cookingHours = "0",
                cookingMinutes = "40",
                ingredients = "500 г куриных грудок, 4 столовые ложки соуса терияки, 2 зубчика чеснока, 1 столовая ложка масла, кунжут для подачи",
                steps = "1. Замаринуйте курицу в соусе терияки и чесноке на 30 минут. 2. Обжарьте курицу на сковороде до готовности. 3. Подавайте с кунжутом.",
                isCreate = true,
                imageUri = "android.resource://com.example.kp_kotlin/" + R.drawable.kat3 // Замените на ваш ресурс изображения
            ),
            Recipe(
                firstName = "Запеченный лосось с лимоном",
                description = "Ароматный лосось, запеченный с лимоном и зеленью.",
                category = "Вторые блюда",
                cookingHours = "0",
                cookingMinutes = "25",
                ingredients = "2 филе лосося, 1 лимон, оливковое масло, соль, перец, зелень",
                steps = "1. Разогрейте духовку до 200°C. 2. Полейте лосось оливковым маслом, посолите и поперчите. 3. Положите ломтики лимона сверху и запекайте 15-20 минут.",
                isCreate = true,
                imageUri = "android.resource://com.example.kp_kotlin/" + R.drawable.kat3 // Замените на ваш ресурс изображения
            ),

            // Салаты
            Recipe(
                firstName = "Салат с курицей и ананасами",
                description = "Сочный салат с курицей, ананасами и майонезом.",
                category = "Салаты",
                cookingHours = "0",
                cookingMinutes = "20",
                ingredients = "200 г куриного филе, 1 банка консервированных ананасов, 100 г майонеза, зелень, соль",
                steps = "1. Отварите куриное филе и нарежьте его. 2. Смешайте курицу, ананасы и майонез. 3. Посолите и добавьте зелень.",
                isCreate = true,
                imageUri = "android.resource://com.example.kp_kotlin/" + R.drawable.kat3 // Замените на ваш ресурс изображения
            ),
            Recipe(
                firstName = "Греческий салат",
                description = "Легкий и свежий греческий салат с фетой и оливками.",
                category = "Салаты",
                cookingHours = "0",
                cookingMinutes = "15",
                ingredients = "2 помидора, 1 огурец, 100 г феты, оливки, оливковое масло, соль, перец, зелень",
                steps = "1. Нарежьте овощи и фету. 2. Смешайте все ингредиенты в салатнице. 3. Заправьте оливковым маслом, посолите и поперчите.",
                isCreate = true,
                imageUri = "android.resource://com.example.kp_kotlin/" + R.drawable.kat3 // Замените на ваш ресурс изображения
            ),

            // Десерты
            Recipe(
                firstName = "Пирожное Наполеон",
                description = "Классическое пирожное с слоеным тестом и кремом.",
                category = "Десерты",
                cookingHours = "1",
                cookingMinutes = "30",
                ingredients = "500 г слоеного теста, 500 мл молока, 200 г сахара, 4 яйца, 50 г муки, ванилин",
                steps = "1. Приготовьте крем, смешав молоко, яйца, сахар и муку. 2. Испеките коржи из теста. 3. Соберите пирожное, чередуя коржи и крем.",
                isCreate = true,
                imageUri = "android.resource://com.example.kp_kotlin/" + R.drawable.kat3 // Замените на ваш ресурс изображения
            ),
            Recipe(
                firstName = "Торт Наполеон",
                description = "Вкусный торт с кремом и хрустящими коржами.",
                category = "Десерты",
                cookingHours = "2",
                cookingMinutes = "0",
                ingredients = "1 упаковка слоеного теста, 1 литр молока, 200 г сахара, 4 яйца, 50 г муки",
                steps = "1. Испеките коржи из слоеного теста. 2. Приготовьте крем из молока, сахара, яиц и муки. 3. Соберите торт, чередуя коржи и крем.",
                isCreate = true,
                imageUri = "android.resource://com.example.kp_kotlin/" + R.drawable.kat3 // Замените на ваш ресурс изображения
            ),

            // Напитки
            Recipe(
                firstName = "Коктейль Мохито",
                description = "Освежающий мятный коктейль с лимоном и газировкой.",
                category = "Напитки",
                cookingHours = "0",
                cookingMinutes = "10",
                ingredients = "10 листиков мяты, 1 лимон, 2 столовые ложки сахара, 200 мл газированной воды, лед",
                steps = "1. В стакане разомните мяту с сахаром и лимонным соком. 2. Добавьте лед и газированную воду. 3. Украсить веточкой мяты.",
                isCreate = true,
                imageUri = "android.resource://com.example.kp_kotlin/" + R.drawable.kat3 // Замените на ваш ресурс изображения
            ),
            Recipe(
                firstName = "Смузи из банана и ягод",
                description = "Питательный смузи из банана и свежих ягод.",
                category = "Напитки",
                cookingHours = "0",
                cookingMinutes = "5",
                ingredients = "1 банан, 100 г ягод (малина, клубника), 200 мл йогурта или молока, мед по вкусу",
                steps = "1. Все ингредиенты поместите в блендер. 2. Измельчите до получения однородной массы. 3. Подавайте холодным.",
                isCreate = true,
                imageUri = "android.resource://com.example.kp_kotlin/" + R.drawable.kat3 // Замените на ваш ресурс изображения
            )

        )
        recipes.forEach { insertNewRecipe(it) }
    }
    override suspend fun getRecipesByCategory(category: String) =
        dao.getRecipesByCategory(category)

    override suspend fun updateRecipe(recipe: Recipe) =
        dao.updateRecipe(recipe)


    override suspend fun deleteRecipe(recipe: Recipe) =
        dao.deleteRecipe(recipe)


    override suspend fun getRecipe(id: Int): Flow<Recipe> =
        dao.getRecipe(id)


    override suspend fun getAllRecipes(): Flow<List<Recipe>> =
        dao.getAllRecipes()


    override suspend fun searchRecipesByKeyword(keyword: String): Flow<List<Recipe>> =
        dao.searchRecipesByKeyword(keyword)


    override suspend fun getFavoriteRecipes(): Flow<List<Recipe>> =
        dao.getFavoriteRecipes()

    override suspend fun getCreateRecipes(): Flow<List<Recipe>> =
        dao.getCreateRecipes()

}