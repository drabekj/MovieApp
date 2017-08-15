package com.strvacademy.drabekj.moviestrv.model.remote

import com.strvacademy.drabekj.moviestrv.model.Movie
import com.strvacademy.drabekj.moviestrv.model.MoviesDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TheMovieDbApiClient {

	@GET("discover/movie?api_key=bd7f681651d1b15fc0fe5a1892ae0151&sort_by=popularity.desc")
	fun getPopularMovies(): Call<MoviesDataResponse>

	@GET("movie/now_playing?api_key=bd7f681651d1b15fc0fe5a1892ae0151")
	fun getNowPlayingMovies(): Call<MoviesDataResponse>

	@GET("movie/{id}?api_key=bd7f681651d1b15fc0fe5a1892ae0151")
	fun getMovieDetailById(@Path("id") movieId: Int): Call<Movie>
}