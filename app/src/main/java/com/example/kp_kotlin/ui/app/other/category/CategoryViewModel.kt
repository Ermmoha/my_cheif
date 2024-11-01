package com.example.kp_kotlin.ui.app.other.category

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kp_kotlin.db.domain.model.Recipe
import com.example.kp_kotlin.db.domain.repository.RecipeRepository
import com.example.kp_kotlin.ui.app.other.category.CategoryDestination.title
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {

    private val _state = MutableStateFlow(CategoryState())
    val state = _state.asStateFlow()

    init {
        getRecipesByCategory()
    }

    private fun getRecipesByCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getRecipesByCategory(title).collect { list ->
                    _state.update { it.copy(recipeList = list) }
                }
            } catch (e: Exception) {
                Log.d("DB_ERROR", e.message.toString())
            }
        }
    }

    data class CategoryState(
        val recipeList: List<Recipe> = listOf()
    )
}