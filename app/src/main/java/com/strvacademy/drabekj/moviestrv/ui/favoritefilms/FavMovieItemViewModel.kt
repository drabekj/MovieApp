package com.strvacademy.drabekj.moviestrv.ui.favoritefilms

import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.model.Movie

class FavMovieItemViewModel(movie: Movie) {
	val movie = ObservableField<Movie>()


	init {
		this.movie.set(movie)
	}
}