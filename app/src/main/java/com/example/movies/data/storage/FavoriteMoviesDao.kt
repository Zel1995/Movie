package com.example.movies.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movies.data.storage.entities.list.FavoriteMovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteMovie(favoriteMovie: FavoriteMovieEntity): Long

    @Query("SELECT * FROM FavoriteMovieEntity")
    fun getAll(): Flow<List<FavoriteMovieEntity>>

    @Query("DELETE FROM FavoriteMovieEntity WHERE id = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT * FROM FavoriteMovieEntity WHERE id = :id")
    suspend fun selectById(id: Int): FavoriteMovieEntity?
}