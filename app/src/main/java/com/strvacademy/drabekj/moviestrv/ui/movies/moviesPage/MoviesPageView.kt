package com.strvacademy.drabekj.moviestrv.ui.movies.moviesPage

import com.strvacademy.drabekj.moviestrv.model.entity.MovieEntity
import com.strvacademy.drabekj.moviestrv.utils.BaseView


interface MoviesPageView : BaseView {
    fun onItemClick(movie: MovieEntity)
}