package com.app.newsappmvvmjetpack.presentation.bottomnavigation

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}