package com.strvacademy.drabekj.moviestrv.ui.moviedetail;

import android.databinding.ObservableField;

import com.strvacademy.drabekj.moviestrv.model.Actor;


public class MovieItemViewModel {
	public final ObservableField<Actor> actor = new ObservableField<>();


	public MovieItemViewModel(Actor actor) {
		this.actor.set(actor);
	}


}
