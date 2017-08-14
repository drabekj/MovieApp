package com.strvacademy.drabekj.moviestrv.ui.profile

import com.strvacademy.drabekj.moviestrv.utils.BaseView


interface ProfileView: BaseView {
	fun onAllFavouriteClick()

	fun onFavMovieClick(movie: String)
}