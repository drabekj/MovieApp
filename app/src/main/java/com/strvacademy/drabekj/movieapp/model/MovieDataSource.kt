package com.strvacademy.drabekj.movieapp.model

import com.strvacademy.drabekj.movieapp.listener.OnLoadDataListener

interface MovieDataSource {

    fun getPopularMovies(listener: OnLoadDataListener<List<Movie>>)

    fun getNowPlayingMovies(listener: OnLoadDataListener<List<Movie>>)

    fun getMovieById(id: Int, listener: OnLoadDataListener<Movie>)


	fun getPopularActors(): Array<Actor>

	fun getActorById(id: Int): Actor?


	fun  getProfile(): Profile?


	fun getFavoriteMoviesByProfile(id: Int): Array<Movie>
}