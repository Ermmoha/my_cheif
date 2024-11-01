package com.example.kp_kotlin.db.domain.repository


import com.example.kp_kotlin.db.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface RecipeRepository{
    suspend fun insertNewRecipe(recipe: Recipe)

    suspend fun insertInitialRecipes()

    suspend fun updateRecipe(recipe: Recipe)

    suspend fun getRecipesByCategory(category: String): Flow<List<Recipe>>

    suspend fun deleteRecipe(recipe: Recipe)

    suspend fun getRecipe(id: Int): Flow<Recipe>

    suspend fun getAllRecipes(): Flow<List<Recipe>>

    suspend fun searchRecipesByKeyword(keyword: String): Flow<List<Recipe>>

    suspend fun getFavoriteRecipes(): Flow<List<Recipe>>

    suspend fun getCreateRecipes(): Flow<List<Recipe>>
}