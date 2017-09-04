package com.strvacademy.drabekj.moviestrv.ui.actors

import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.model.entity.ActorEntity

class ActorsItemViewModel(actor: ActorEntity) {
	val actor = ObservableField<ActorEntity>()


	init {
		this.actor.set(actor)
	}


}