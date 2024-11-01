package com.example.kp_kotlin.di

import android.content.Context
import androidx.room.Room
import com.example.kp_kotlin.db.RecipeDatabase
import com.example.kp_kotlin.db.data.repository.RecipeRepositoryImpl
import com.example.kp_kotlin.db.domain.repository.RecipeDao
import com.example.kp_kotlin.db.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDBModule {

    @Singleton
    @Provides
    fun provideRecipeDB(
@ApplicationContext context: Context
    ): RecipeDatabase = Room.databaseBuilder(
        context,
        RecipeDatabase :: class.java,
        "recipe_DB"
    )
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideRecipeDao(db: RecipeDatabase): RecipeDao = db.getRecipeDao()

    @Singleton
    @Provides
    fun provideRecipeRepository(dao: RecipeDao): RecipeRepository = RecipeRepositoryImpl(dao)
}