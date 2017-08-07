package com.strvacademy.drabekj.moviestrv.mainscreen.popular

import com.strvacademy.drabekj.moviestrv.mainscreen.MainFragment


class PopularMoviesFragment: MainFragment<PopularMoviesViewModel>() {
	override fun getViewModelClass(): Class<PopularMoviesViewModel> {
		return PopularMoviesViewModel::class.java
	}
}