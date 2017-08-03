package com.strvacademy.drabekj.moviestrv.model.local

import com.strvacademy.drabekj.moviestrv.model.Movie

interface MovieLocalDataSource {

    fun getPopularMovies(): Array<Movie>

    fun getNowPlayingMovies(): Array<Movie>

    fun getMovieById(id: Int): Movie?
}