package com.strvacademy.drabekj.movieapp.ui.profile

import com.strvacademy.drabekj.movieapp.model.entity.MovieEntity
import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseView


interface ProfileView: BaseView {
	fun onAllFavouriteClick()

	fun onFavMovieClick(movie: MovieEntity)
}