package com.strvacademy.drabekj.moviestrv.actorsActivity

import com.strvacademy.drabekj.moviestrv.model.Actor
import com.strvacademy.drabekj.moviestrv.utils.BaseView


interface ActorsView: BaseView {
	fun onActorClick(actor: Actor)
}