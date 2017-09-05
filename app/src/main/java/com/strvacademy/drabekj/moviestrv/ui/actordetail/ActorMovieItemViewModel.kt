package com.strvacademy.drabekj.moviestrv.ui.actordetail

import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.model.entity.CastEntity

class ActorMovieItemViewModel(cast: CastEntity) {
	val cast = ObservableField<CastEntity>()


	init {
		this.cast.set(cast)
	}


}