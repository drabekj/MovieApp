package com.strvacademy.drabekj.moviestrv.ui.actordetail

import com.strvacademy.drabekj.moviestrv.model.entity.CastEntity
import com.strvacademy.drabekj.moviestrv.utils.BaseView


interface ActorDetailView: BaseView {
	fun onAllCreditsClick(actorId: Int)
	fun onMovieClick(item: CastEntity)
}