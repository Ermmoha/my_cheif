package com.example.kp_kotlin.db.data.repository

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