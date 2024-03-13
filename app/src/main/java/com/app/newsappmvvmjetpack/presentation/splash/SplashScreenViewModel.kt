package com.app.newsappmvvmjetpack.presentation.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.newsappmvvmjetpack.common.Resource
import com.app.newsappmvvmjetpack.domain.usecase.get_settings.GetSettingsUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(private val getSettingsUsecase: GetSettingsUsecase) :
    ViewModel() {
    private val _state = mutableStateOf(SplashScreenState())
    val state: State<SplashScreenState> = _state

    init {
        getCoins()
    }

    private fun getCoins() {
        getSettingsUsecase().onEach { result ->
            when (result) {

                is Resource.Success -> {
                    _state.value = SplashScreenState(coins = result.data)
                }

                is Resource.Error -> {
                    _state.value = SplashScreenState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }

                is Resource.Loading -> {
                    _state.value = SplashScreenState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}

