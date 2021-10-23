package com.example.movies.data

import android.os.Handler
import android.os.Looper
import com.example.movies.domain.Movie
import com.example.movies.domain.MovieRepository
import java.lang.RuntimeException
import java.util.concurrent.Executors

class MovieRepositoryImpl:MovieRepository {
    private val executor = Executors.newSingleThreadExecutor()
    private val handler = Handler(Looper.getMainLooper())

    override fun getMovie(callback: (result: RepositoryResult<Movie>) -> Unit) {
        executor.execute{
            Thread.sleep(1000)
            if(true){
                handler.post {
                    callback(Success(Movie("0","name,")))
                }
            }else{
                handler.post{
                    callback(Error(RuntimeException("Exc")))
                }
            }
        }
    }
    override fun shutDown(){
        executor.shutdown()
    }

}

sealed class RepositoryResult<T>
data class Success<T>(val value:T):RepositoryResult<T>()
data class Error<T>(val error:Throwable): RepositoryResult<T>()