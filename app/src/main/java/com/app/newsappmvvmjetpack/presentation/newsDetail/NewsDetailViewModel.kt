package com.app.newsappmvvmjetpack.presentation.newsDetail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.newsappmvvmjetpack.common.Constants
import com.app.newsappmvvmjetpack.common.Resource
import com.app.newsappmvvmjetpack.domain.usecase.get_category.GetCategoriesUsecase
import com.app.newsappmvvmjetpack.domain.usecase.get_news_detail.GetNewsDetailUseCase
import com.app.newsappmvvmjetpack.presentation.bottomnavigation.screens.categories.CategoryScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewsDetailViewModel @Inject constructor(
    private val getNewsDetailUseCase: GetNewsDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _state = mutableStateOf(NewsDetailState())
    val state: State<NewsDetailState> = _state

    init {
        savedStateHandle.get<String>(Constants.PARAM_ID)?.let { coinId ->
            getNewsDetail(coinId.toInt())
        }

    }

    private fun getNewsDetail(id: Int) {
        getNewsDetailUseCase(id).onEach { result ->
            when (result) {

                is Resource.Success -> {
                    _state.value = NewsDetailState(newsDetail = result.data)
                }

                is Resource.Error -> {
                    _state.value = NewsDetailState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = NewsDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}