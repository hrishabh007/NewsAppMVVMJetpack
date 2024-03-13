package com.app.newsappmvvmjetpack.presentation.bottomnavigation.screens.recentpost

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.newsappmvvmjetpack.common.Resource
import com.app.newsappmvvmjetpack.domain.model.getRecentPost.GetRecentPost
import com.app.newsappmvvmjetpack.domain.usecase.get_recent_post.GetRecentPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecentPostScreenViewModel @Inject constructor(private val getRecentPostUseCase: GetRecentPostUseCase) :
    ViewModel() {
    private val _state = mutableStateOf(RecentPostScreenState())
    val state: State<RecentPostScreenState> = _state

    init {
        getCoins(page = 1, count = 10)
    }
    fun onEvent(event: GetRecentPostEvent) {
        viewModelScope.launch {
            when (event) {
                is GetRecentPostEvent.GetRecentPost -> {
                    getCoins(1,10)
                }

                else -> {}
            }
        }
    }

     fun getCoins(page: Int, count: Int) {
        getRecentPostUseCase(page = page, count = count).onEach { result ->
            when (result) {

                is Resource.Success -> {
                    _state.value = RecentPostScreenState(coins = result.data)
                }

                is Resource.Error -> {
                    _state.value = RecentPostScreenState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = RecentPostScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}
data class RecentPostScreenState(
    val isLoading: Boolean = false,
    val coins: GetRecentPost? = null,
    val error: String = ""
)
sealed class GetRecentPostEvent {
    object GetRecentPost : GetRecentPostEvent()
}
