package com.strvacademy.drabekj.movieapp.ui.actordetail

import com.strvacademy.drabekj.movieapp.model.entity.CastEntity
import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseView


interface ActorDetailView: BaseView {
	fun onAllCreditsClick(actorId: Int)
	fun onMovieClick(item: CastEntity)
}