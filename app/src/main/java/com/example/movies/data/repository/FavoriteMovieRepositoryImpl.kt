package com.example.movies.data.repository

import com.example.movies.data.mapper.FavoriteMovieEntityMapper
import com.example.movies.data.storage.FavoriteMoviesDao
import com.example.movies.domain.repository.FavoriteMovieRepository
import com.example.movies.domain.model.movie.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class FavoriteMovieRepositoryImpl(
    private val favoriteMoviesDao: FavoriteMoviesDao,
    private val favoriteMovieEntityMapper: FavoriteMovieEntityMapper
) :
    FavoriteMovieRepository {
    override fun getFavoriteMovies(): Flow<RepositoryResult<List<Movie>>> {
        try {
            return favoriteMoviesDao.getAll()
                .map { Success(favoriteMovieEntityMapper.toMovieList(it)) }
        } catch (exc: Exception) {
            return flow { emit(Error(exc)) }
        }
    }


    override suspend fun deleteFavoriteMovieById(id: Int) {
        favoriteMoviesDao.deleteById(id)
    }

    override suspend fun addFavoriteMovie(movie: Movie) {
        favoriteMoviesDao.addFavoriteMovie(favoriteMovieEntityMapper.toFavoriteMovie(movie))
    }

    override suspend fun getFavoriteMovieById(id: Int): Movie? {
        return favoriteMovieEntityMapper.toNullableMovie(favoriteMoviesDao.selectById(id))
    }

}