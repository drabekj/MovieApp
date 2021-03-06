package com.strvacademy.drabekj.movieapp.model.remote

import com.strvacademy.drabekj.movieapp.model.Movie
import com.strvacademy.drabekj.movieapp.model.MoviesDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface TheMovieDbApiService {

	@GET("discover/movie?api_key=bd7f681651d1b15fc0fe5a1892ae0151&sort_by=popularity.desc")
	fun getPopularMovies(): Call<MoviesDataResponse>

	@GET("movie/now_playing?api_key=bd7f681651d1b15fc0fe5a1892ae0151")
	fun getNowPlayingMovies(): Call<MoviesDataResponse>

	@GET("movie/{id}?api_key=bd7f681651d1b15fc0fe5a1892ae0151")
	fun getMovieDetailById(@Path("id") movieId: Int): Call<Movie>
}