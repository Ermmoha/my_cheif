package com.example.kp_kotlin.ui.app.other.card

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
class CardPreviewViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RecipeState())
    val state = _state.asStateFlow()

    fun updateState(recipe: Recipe) {
        _state.update { it.copy(recipe = recipe) }
    }

    init {
        getRecipe()
    }

    private fun getRecipe() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getRecipe(CardDestination.cardId).collect { recipe ->
                    _state.update { it.copy(recipe = recipe) }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun updateRecipe(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.updateRecipe(state.value.recipe)
            } catch (e: Exception) {
            e.printStackTrace()
        }
    } }

    fun deleteRecipe() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.deleteRecipe(state.value.recipe)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    data class RecipeState(
        val recipe: Recipe = Recipe(
            firstName = String(),
            description = String(),
            category = String(),
            cookingHours = String(),
            cookingMinutes = String(),
            ingredients = String(),
            steps = String(),
            isFavorite = false,
            imageUri = String()
        )
    )
}