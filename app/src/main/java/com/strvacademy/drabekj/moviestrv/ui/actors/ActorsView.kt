package com.strvacademy.drabekj.moviestrv.ui.actors

import com.strvacademy.drabekj.moviestrv.model.entity.ActorEntity
import com.strvacademy.drabekj.moviestrv.utils.BaseView


interface ActorsView: BaseView {
	fun onActorClick(actor: ActorEntity)
}