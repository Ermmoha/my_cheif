package com.example.kp_kotlin.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.kp_kotlin.db.domain.model.Recipe
import com.example.kp_kotlin.db.domain.repository.RecipeDao

@Database(
    entities = [Recipe::class],
    version = 7,
    exportSchema = false
)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun getRecipeDao(): RecipeDao

}