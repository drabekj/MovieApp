package com.strvacademy.drabekj.moviestrv.ui.actors

import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.model.Actor

class ActorsItemViewModel(actor: Actor) {
	val actor = ObservableField<Actor>()


	init {
		this.actor.set(actor)
	}


}