package com.app.newsappmvvmjetpack.presentation.bottomnavigation.screens.categories

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.newsappmvvmjetpack.common.Resource
import com.app.newsappmvvmjetpack.domain.usecase.get_category.GetCategoriesUsecase
import com.app.newsappmvvmjetpack.presentation.splash.SplashScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val getCategoriesUsecase: GetCategoriesUsecase) : ViewModel() {
    private val _state = mutableStateOf(CategoryScreenState())
    val state: State<CategoryScreenState> = _state

    init {
        getCategory()
    }

    private fun getCategory() {
        getCategoriesUsecase().onEach { result ->
            when (result) {

                is Resource.Success -> {
                    _state.value = CategoryScreenState(coins = result.data)
                }

                is Resource.Error -> {
                    _state.value = CategoryScreenState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = CategoryScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}