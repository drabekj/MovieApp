package com.strvacademy.drabekj.moviestrv.model.remote.rest.provider

import com.strvacademy.drabekj.moviestrv.model.entity.*
import com.strvacademy.drabekj.moviestrv.model.remote.rest.RetrofitClient
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


object MovieServiceProvider {
	val MOVIE_CALL_TYPE = "movie"
	val MOVIE_CREDITS_CALL_TYPE = "movie_credits"
	val MOVIE_IMAGES_CALL_TYPE = "movie_images"
	val MOVIE_VIDEOS_CALL_TYPE = "movie_videos"
	val POPULAR_MOVIES_CALL_TYPE = "movies_popular"
	val NOW_PLAYING_MOVIES_CALL_TYPE = "movies_now_playing"
	val SEARCH_MOVIE_CALL_TYPE = "search_movie"

	@Volatile private var sService: MovieService? = null


	interface MovieService {
		@GET("movie/{id}")
		fun movie(@Path("id") id: Int): Call<MovieEntity>

		@GET("movie/{id}/credits")
		fun movieCredits(@Path("id") id: Int): Call<CreditsEntity>

		@GET("movie/{id}/images")
		fun movieImages(@Path("id") id: Int): Call<ImagesEntity>

		@GET("movie/{id}/videos")
		fun movieVideos(@Path("id") id: Int): Call<VideosResultsEntity>

		@GET("discover/movie?sort_by=popularity.desc")
		fun popularMovies(): Call<MovieResultsEntity>

		@GET("movie/now_playing")
		fun nowPlayingMovies(): Call<MovieResultsEntity>

		@GET("search/movie")
		fun searchMovie(@Query("query") query : String): Call<MovieResultsEntity>
	}


	val service: MovieService
		get() {
			if (sService == null) {
				synchronized(MovieServiceProvider::class.java) {
					if (sService == null) {
						sService = RetrofitClient.createService(MovieService::class.java)
					}
				}
			}
			return sService!!
		}
}