package com.strvacademy.drabekj.movieapp.ui.allFilms

import android.databinding.ObservableField
import com.strvacademy.drabekj.movieapp.model.entity.CastEntity

class FilmItemViewModel(item: CastEntity) {
	val item = ObservableField<CastEntity>()

	init {
		this.item.set(item)
	}
}