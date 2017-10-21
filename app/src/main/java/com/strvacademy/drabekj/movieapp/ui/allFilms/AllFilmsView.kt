package com.strvacademy.drabekj.movieapp.ui.allFilms

import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseView


interface AllFilmsView : BaseView {
	fun onMovieClick(movieId: Int)
}