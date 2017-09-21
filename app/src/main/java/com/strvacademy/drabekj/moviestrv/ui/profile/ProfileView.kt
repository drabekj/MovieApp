package com.strvacademy.drabekj.moviestrv.ui.profile

import com.strvacademy.drabekj.moviestrv.model.entity.MovieEntity
import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseView


interface ProfileView: BaseView {
	fun onAllFavouriteClick()

	fun onFavMovieClick(movie: MovieEntity)
}