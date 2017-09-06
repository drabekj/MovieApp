package com.strvacademy.drabekj.moviestrv.ui.allFilms

import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.model.Movie

class FilmItemViewModel(movie: Movie) {
	val movie = ObservableField<Movie>()


	init {
		this.movie.set(movie)
	}
}