package com.strvacademy.drabekj.moviestrv.model

interface MovieDataSource {

    fun getPopularMovies(): Array<Movie>

    fun getNowPlayingMovies(): List<Movie>

    fun getMovieById(id: Int): Movie?


	fun getPopularActors(): Array<Actor>

	fun getActorById(id: Int): Actor?


	fun  getProfile(): Profile?


	fun getFavoriteMoviesByProfile(id: Int): Array<Movie>
}