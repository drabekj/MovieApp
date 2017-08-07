package com.strvacademy.drabekj.moviestrv.mainscreen.moviesPage.popular

import com.strvacademy.drabekj.moviestrv.mainscreen.moviesPage.MoviesPageViewModel
import org.alfonz.view.StatefulLayout


class PopularMoviesViewModel: MoviesPageViewModel() {

	override fun loadData() {
		// show progress
		state.set(StatefulLayout.PROGRESS)

		// load data from data provider...
		onLoadData(dataSource.getPopularMovies())
	}
}