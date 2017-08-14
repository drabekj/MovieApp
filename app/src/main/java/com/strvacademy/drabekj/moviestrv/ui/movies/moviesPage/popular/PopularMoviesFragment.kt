package com.strvacademy.drabekj.moviestrv.ui.movies.moviesPage.popular

import com.strvacademy.drabekj.moviestrv.ui.movies.moviesPage.MoviesPageFragment


class PopularMoviesFragment: MoviesPageFragment<PopularMoviesViewModel>() {

	override fun getViewModelClass(): Class<PopularMoviesViewModel> {
		return PopularMoviesViewModel::class.java
	}


	companion object {
		fun newInstance(): PopularMoviesFragment {
			return PopularMoviesFragment()
		}
	}
}