package com.example.kp_kotlin.db.domain.repository

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.kp_kotlin.db.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    // Вставить новый рецепт
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNewRecipe(recipe: Recipe)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertInitialRecipes(recipes: List<Recipe>)

    // Обновить рецепт
    @Update
    suspend fun updateRecipe(recipe: Recipe)

        // Удалить рецепт
    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    // Получить рецепт по идентификатору
    @Query("SELECT * from recipes WHERE id = :id")
    fun getRecipe(id: Int): Flow<Recipe>

    @Query("SELECT * from recipes WHERE category = :category")
    fun getRecipesByCategory(category: String):Flow<List<Recipe>>

    // Получить все рецепты (по алфавиту)
    @Query("SELECT * from recipes ORDER BY first_name ASC")
    fun getAllRecipes(): Flow<List<Recipe>>

    // Получить рецепты по ключевому слову (поиск по названию)
    @Query("SELECT * FROM recipes WHERE first_name LIKE '%' || :keyword || '%'")
    fun searchRecipesByKeyword(keyword: String): Flow<List<Recipe>>

    // Получить избранные рецепты
    @Query("SELECT * from recipes WHERE isFavorite = 1")
    fun getFavoriteRecipes(): Flow<List<Recipe>>

    //Получить созданные рецепты
    @Query("SELECT * from recipes WHERE isCreate = 1")
    fun getCreateRecipes(): Flow<List<Recipe>>
}