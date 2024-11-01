package com.example.kp_kotlin.ui.app.other.creation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kp_kotlin.db.domain.model.Recipe
import com.example.kp_kotlin.db.domain.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeCreationViewModel @Inject constructor(
    private val repository: RecipeRepository
): ViewModel() {

    private val _state = MutableStateFlow(RecipeCreationState())
    val state = _state.asStateFlow()

    fun updateState(recipe: Recipe) {
        _state.update { it.copy(recipe = recipe) }
    }

    fun insertNewRecipe() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.insertNewRecipe(state.value.recipe)
            } catch (e: Exception){
                Log.d("ERROR", "BULLSHIT")
            }
        }
    }

    data class RecipeCreationState(
        val recipe: Recipe = Recipe(
            firstName = "",
            description = "",
            category = "",
            cookingHours = "",
            cookingMinutes = "",
            ingredients = "",
            steps = "",
            isCreate = true,
            imageUri = ""
        )
    )
}