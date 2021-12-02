package com.example.movies.data.network

import okhttp3.Interceptor
import okhttp3.Response

class CustomInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("RequestMovies", "get movies from tmdb")
            .build()
        return chain.proceed(request)
    }
}