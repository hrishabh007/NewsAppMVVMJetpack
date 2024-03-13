package com.app.newsappmvvmjetpack.domain.usecase

interface BaseUseCase<In, Out>{
    suspend fun execute(input: In): Out
}