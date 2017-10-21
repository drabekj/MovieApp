package com.strvacademy.drabekj.movieapp.model.local

import com.strvacademy.drabekj.movieapp.model.Actor
import com.strvacademy.drabekj.movieapp.model.Movie
import com.strvacademy.drabekj.movieapp.model.Profile

interface MovieLocalDataSource {

    fun getPopularMovies(): Array<Movie>

    fun getNowPlayingMovies(): List<Movie>

    fun getMovieById(id: Int): Movie?


	fun getPopularActors(): Array<Actor>

	fun getActorById(id: Int): Actor?


	fun  getProfile(): Profile?


	fun getFavoriteMoviesByProfile(id: Int): Array<Movie>
}