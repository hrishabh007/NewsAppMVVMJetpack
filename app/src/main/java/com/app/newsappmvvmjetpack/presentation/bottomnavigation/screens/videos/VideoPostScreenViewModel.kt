package com.app.newsappmvvmjetpack.presentation.bottomnavigation.screens.videos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.app.newsappmvvmjetpack.data.remote.dto.getRecentPost.RecentPost
import com.app.newsappmvvmjetpack.domain.usecase.get_video_post.GetVideoPostUseCase
import com.app.newsappmvvmjetpack.presentation.bottomnavigation.screens.recentpost.RecentPostScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoPostScreenViewModel @Inject constructor(private val getVideoPostUseCase: GetVideoPostUseCase) : ViewModel() {
    private val _state = mutableStateOf(VideoPostScreenState())
    val state: State<VideoPostScreenState> = _state
    private val _videoState: MutableStateFlow<PagingData<RecentPost>> = MutableStateFlow(value = PagingData.empty())
    val videoState: MutableStateFlow<PagingData<RecentPost>> get() = _videoState

    init {
        onEvent(GetVideoPostEvent.GetVideoPost)
    }

    fun onEvent(event: GetVideoPostEvent) {
        viewModelScope.launch {
            when (event) {
                is GetVideoPostEvent.GetVideoPost -> {
                    getVideoPost()
                    //  getCoins()
                }

                else -> {}
            }
        }
    }

    private suspend fun getVideoPost() {
        getVideoPostUseCase.execute(Unit)
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect {
                videoState.value = it
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

sealed class GetVideoPostEvent {
    object GetVideoPost : GetVideoPostEvent()

}