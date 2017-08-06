package com.strvacademy.drabekj.moviestrv.model

interface MovieDataSource {

    fun getPopularMovies(): Array<Movie>

    fun getNowPlayingMovies(): Array<Movie>

    fun getMovieById(id: Int): Movie?

	fun getPopularActors(): Array<Actor>
}