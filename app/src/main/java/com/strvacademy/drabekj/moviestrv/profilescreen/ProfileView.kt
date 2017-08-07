package com.strvacademy.drabekj.moviestrv.profilescreen

import com.strvacademy.drabekj.moviestrv.model.Movie
import com.strvacademy.drabekj.moviestrv.utils.BaseView


interface ProfileView: BaseView {
	fun onAllFavouriteClick()

	fun onFavMovieClick(movie: String)
}