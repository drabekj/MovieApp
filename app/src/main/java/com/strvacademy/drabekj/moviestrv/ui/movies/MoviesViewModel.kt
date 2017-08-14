package com.strvacademy.drabekj.moviestrv.ui.movies

import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.utils.BaseViewModel


class MoviesViewModel : BaseViewModel<MoviesView>() {
	val state = ObservableField<Int>()
}