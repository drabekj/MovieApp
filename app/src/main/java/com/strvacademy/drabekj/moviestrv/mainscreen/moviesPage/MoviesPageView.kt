package com.strvacademy.drabekj.moviestrv.mainscreen.moviesPage

import com.strvacademy.drabekj.moviestrv.model.Movie
import com.strvacademy.drabekj.moviestrv.utils.BaseView


interface MoviesPageView : BaseView {
    fun onItemClick(movie: Movie)
}