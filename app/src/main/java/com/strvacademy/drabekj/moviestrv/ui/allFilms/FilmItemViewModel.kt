package com.strvacademy.drabekj.moviestrv.ui.allFilms

import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.model.entity.CastEntity

class FilmItemViewModel(item: CastEntity) {
	val item = ObservableField<CastEntity>()


	init {
		this.item.set(item)
	}
}