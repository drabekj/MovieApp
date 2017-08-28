package com.strvacademy.drabekj.moviestrv.model

import com.strvacademy.drabekj.moviestrv.ui.movies.moviesPage.MoviesPageViewModel

interface MovieDataSource {

    fun getPopularMovies(listener: MoviesPageViewModel.onLoadDataListener)

    fun getNowPlayingMovies(): List<Movie>

    fun getMovieById(id: Int): Movie?


	fun getPopularActors(): Array<Actor>

	fun getActorById(id: Int): Actor?


	fun  getProfile(): Profile?


	fun getFavoriteMoviesByProfile(id: Int): Array<Movie>
}