package com.strvacademy.drabekj.movieapp.ui.actors

import android.databinding.ObservableField
import com.strvacademy.drabekj.movieapp.model.entity.ActorEntity

class ActorsItemViewModel(actor: ActorEntity) {
	val actor = ObservableField<ActorEntity>()


	init {
		this.actor.set(actor)
	}


}