package com.example.movies.domain.usecase

import com.example.movies.R
import com.example.movies.domain.FavoriteMovieRepository
import com.example.movies.domain.model.Movie
import javax.inject.Inject

class AddOrDeleteFavoriteMovieUseCase @Inject constructor(
    private val favoriteRepository: FavoriteMovieRepository
) {
    suspend fun run(movie: Movie): Int {
        return if(favoriteRepository.getFavoriteMovieById(movie.id)!= null){
            favoriteRepository.deleteFavoriteMovieById(movie.id)
            R.drawable.ic_like
        }else{
            favoriteRepository.addFavoriteMovie(movie)
            R.drawable.ic_liked
        }
    }
}