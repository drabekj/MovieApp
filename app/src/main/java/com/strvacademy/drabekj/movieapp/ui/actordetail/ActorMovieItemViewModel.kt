package com.strvacademy.drabekj.movieapp.ui.actordetail

import android.databinding.ObservableField
import com.strvacademy.drabekj.movieapp.model.entity.CastEntity

class ActorMovieItemViewModel(cast: CastEntity) {
	val cast = ObservableField<CastEntity>()


	init {
		this.cast.set(cast)
	}


}