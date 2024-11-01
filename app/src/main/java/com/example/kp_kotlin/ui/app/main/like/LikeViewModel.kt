package com.example.kp_kotlin.ui.app.main.like

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
class LikeViewModel @Inject constructor(
    private val repository: RecipeRepository
): ViewModel(){
      private val _state = MutableStateFlow(LikeState())
      val state = _state.asStateFlow()

    init {
        getCreateRecipes()
        getFavoriteRecipes()
    }

      private fun getCreateRecipes(){
          viewModelScope.launch(Dispatchers.IO) {
              try {
                  repository.getCreateRecipes().collect {list ->
                      _state.update {it.copy(recipeCreateList = list)}
                  }
              } catch (e: Exception) {
                  Log.d("DB_ERROR", e.message.toString())
              }
          }
      }

    private fun getFavoriteRecipes(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.getFavoriteRecipes().collect {list ->
                    _state.update {it.copy(recipeFavoriteList = list)}
                }
            } catch (e: Exception) {
                Log.d("DB_ERROR", e.message.toString())
            }
        }
    }
    data class LikeState(
        val recipeCreateList: List<Recipe> = listOf(),
        val recipeFavoriteList: List<Recipe> = listOf()
    )
}