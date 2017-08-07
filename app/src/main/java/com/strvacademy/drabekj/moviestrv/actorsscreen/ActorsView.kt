package com.strvacademy.drabekj.moviestrv.actorsscreen

import com.strvacademy.drabekj.moviestrv.model.Actor
import com.strvacademy.drabekj.moviestrv.utils.BaseView


interface ActorsView: BaseView {
	fun onActorClick(actor: Actor)
}