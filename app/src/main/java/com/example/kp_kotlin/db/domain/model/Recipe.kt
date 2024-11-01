package com.example.kp_kotlin.db.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    @NotNull
    val id: Int = 0,
    @ColumnInfo("first_name")
    val firstName: String,
    val description: String,
    val category: String,
    @ColumnInfo("cooking_hours")
    val cookingHours: String,
    @ColumnInfo("cooking_minutes")
    val cookingMinutes: String,
    val ingredients: String,
    val steps: String,
    val isFavorite: Boolean = false,
    val isCreate: Boolean = false,
    val imageUri: String? = null
)
