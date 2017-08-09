package com.strvacademy.drabekj.moviestrv.actordetailscreen

import com.strvacademy.drabekj.moviestrv.utils.BaseView


interface ActorDetailView: BaseView {
	fun onAllCreditsClick()
	fun onMovieClick(name: String)
}