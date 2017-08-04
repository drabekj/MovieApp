package com.strvacademy.drabekj.moviestrv.mainActivity.popular

import com.strvacademy.drabekj.moviestrv.mainActivity.MainFragment


class PopularMoviesFragment: MainFragment<PopularMoviesViewModel>() {
	override fun getViewModelClass(): Class<PopularMoviesViewModel> {
		return PopularMoviesViewModel::class.java
	}
}