package com.strvacademy.drabekj.moviestrv.model

import com.strvacademy.drabekj.moviestrv.model.local.MovieLocalDataSource


class MovieRepository: MovieDataSource {

    private val mMoviesLocalDataSource: MovieLocalDataSource


    constructor(localDataSource: MovieLocalDataSource) {
        mMoviesLocalDataSource = localDataSource
    }

    override fun getPopularMovies(): Array<Movie> {
        return mMoviesLocalDataSource.getPopularMovies()
    }

    override fun getNowPlayingMovies(): Array<Movie> {
        return mMoviesLocalDataSource.getNowPlayingMovies()
    }

    override fun getMovieById(id: Int): Movie? {
        return mMoviesLocalDataSource.getMovieById(id)
    }
}