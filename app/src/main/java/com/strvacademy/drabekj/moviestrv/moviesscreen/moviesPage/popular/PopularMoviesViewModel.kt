package com.strvacademy.drabekj.moviestrv.moviesscreen.moviesPage.popular

import com.strvacademy.drabekj.moviestrv.moviesscreen.moviesPage.MoviesPageViewModel
import org.alfonz.view.StatefulLayout


class PopularMoviesViewModel: MoviesPageViewModel() {

	override fun loadData() {
		// show progress
		state.set(StatefulLayout.PROGRESS)

		// load data from data provider...
		onLoadData(dataSource.getPopularMovies())
	}
}