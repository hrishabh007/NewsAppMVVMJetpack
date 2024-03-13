package com.app.newsappmvvmjetpack.presentation.bottomnavigation.screens.recentpost

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.newsappmvvmjetpack.common.Resource
import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.RecentPost
import com.app.newsappmvvmjetpack.domain.model.getRecentPost.GetRecentPost
import com.app.newsappmvvmjetpack.domain.usecase.get_recent_post.GetRecentPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecentPostScreenViewModel @Inject constructor(private val getRecentPostUseCase: GetRecentPostUseCase) :
    ViewModel() {
    private val _state = mutableStateOf(RecentPostScreenState())
    val state: State<RecentPostScreenState> = _state
    private val _moviesState: MutableStateFlow<PagingData<RecentPost>> = MutableStateFlow(value = PagingData.empty())
    val moviesState: MutableStateFlow<PagingData<RecentPost>> get() = _moviesState
    init {
        onEvent(GetRecentPostEvent.GetRecentPost)
    }
    fun onEvent(event: GetRecentPostEvent) {
        viewModelScope.launch {
            when (event) {
                is GetRecentPostEvent.GetRecentPost -> {
                   getRecentPost()
                  //  getCoins()
                }

                else -> {}
            }
        }
    }
    private suspend fun getRecentPost() {
        getRecentPostUseCase.execute(Unit)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                _moviesState.value = it
            }
    }

//     fun getCoins() {
//        getRecentPostUseCase().onEach { result ->
//            when (result) {
//
//                is Resource.Success -> {
//                    _state.value = RecentPostScreenState(coins = result.data)
//
//                }
//
//                is Resource.Error -> {
//
//                    _state.value = RecentPostScreenState(
//                        error = result.message ?: "An unexpected error occured"
//                    )
//                }
//
//                is Resource.Loading -> {
//                    _state.value = RecentPostScreenState(isLoading = true)
//                }
//            }
//        }.launchIn(viewModelScope)
//    }
}

sealed class GetRecentPostEvent {
    object GetRecentPost : GetRecentPostEvent()
}
