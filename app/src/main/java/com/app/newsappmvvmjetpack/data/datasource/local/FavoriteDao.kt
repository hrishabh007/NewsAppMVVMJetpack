package com.example.rickandmorty.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.newsappmvvmjetpack.data.datasource.local.DetailPost

import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite")
    fun getPost(): Flow<List<DetailPost>>

    @Query("SELECT * FROM favorite WHERE nid = :id")
    fun getPost(id: Int): LiveData<DetailPost>

    @Query("SELECT EXISTS(SELECT * FROM favorite  WHERE nid = :id)")
    fun isExists(id: Int): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(characters: List<DetailPost>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: DetailPost)

    @Query("DELETE from favorite where nid = :id ")
    suspend fun delete(id: Int)

}