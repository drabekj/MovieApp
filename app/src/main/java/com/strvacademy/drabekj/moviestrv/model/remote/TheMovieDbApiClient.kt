package com.strvacademy.drabekj.moviestrv.model.remote

import com.strvacademy.drabekj.moviestrv.model.Movie
import com.strvacademy.drabekj.moviestrv.model.PopularMoviesDataResponse
import retrofit2.Call
import retrofit2.http.GET


interface TheMovieDbApiClient {

	@GET("movie?api_key=bd7f681651d1b15fc0fe5a1892ae0151&sort_by=popularity.desc")
	fun getPopularMovies(): Call<PopularMoviesDataResponse>
}