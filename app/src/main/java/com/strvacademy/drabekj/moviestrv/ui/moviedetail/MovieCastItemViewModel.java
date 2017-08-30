package com.strvacademy.drabekj.moviestrv.ui.moviedetail;

import android.databinding.ObservableField;

import com.strvacademy.drabekj.moviestrv.model.entity.CastEntity;


public class MovieCastItemViewModel {
	public final ObservableField<CastEntity> actor = new ObservableField<>();


	public MovieCastItemViewModel(CastEntity actor) {
		this.actor.set(actor);
	}
}
