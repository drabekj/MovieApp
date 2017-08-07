package com.strvacademy.drabekj.moviestrv.moviesscreen

import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.utils.BaseViewModel


class MoviesViewModel : BaseViewModel<MoviesView>() {
	val state = ObservableField<Int>()
}