package com.strvacademy.drabekj.moviestrv.ui.profile;

import android.databinding.ObservableField;

import com.strvacademy.drabekj.moviestrv.model.entity.CastEntity;
import com.strvacademy.drabekj.moviestrv.model.entity.MovieEntity;


public class ProfileFavMovieItemViewModel {
	public final ObservableField<MovieEntity> movie = new ObservableField<>();


	public ProfileFavMovieItemViewModel(MovieEntity movie) {
		this.movie.set(movie);
	}
}
