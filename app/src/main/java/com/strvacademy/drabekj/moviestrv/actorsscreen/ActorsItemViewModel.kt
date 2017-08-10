package com.strvacademy.drabekj.moviestrv.actorsscreen

import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.model.Actor

class ActorsItemViewModel(actor: Actor) {
	val actor = ObservableField<Actor>()


	init {
		this.actor.set(actor)
	}


}