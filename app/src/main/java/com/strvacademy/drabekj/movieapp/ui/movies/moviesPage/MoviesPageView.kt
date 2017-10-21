package com.strvacademy.drabekj.movieapp.ui.movies.moviesPage

import com.strvacademy.drabekj.movieapp.model.entity.MovieEntity
import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseView


interface MoviesPageView : BaseView {
    fun onItemClick(movie: MovieEntity)
}