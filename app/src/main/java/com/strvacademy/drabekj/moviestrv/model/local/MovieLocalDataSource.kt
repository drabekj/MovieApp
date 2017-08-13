package com.strvacademy.drabekj.moviestrv.model.local

import com.strvacademy.drabekj.moviestrv.model.Actor
import com.strvacademy.drabekj.moviestrv.model.Movie
import com.strvacademy.drabekj.moviestrv.model.Profile

interface MovieLocalDataSource {

    fun getPopularMovies(): Array<Movie>

    fun getNowPlayingMovies(): Array<Movie>

    fun getMovieById(id: Int): Movie?


	fun getPopularActors(): Array<Actor>

	fun getActorById(id: Int): Actor?


	fun  getProfile(): Profile?


	fun getFavoriteMoviesByProfile(id: Int): Array<Movie>
}