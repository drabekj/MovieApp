package com.strvacademy.drabekj.moviestrv.mainscreen

import com.strvacademy.drabekj.moviestrv.model.Movie
import com.strvacademy.drabekj.moviestrv.utils.BaseView


interface MainView: BaseView {
    fun onItemClick(movie: Movie)
}