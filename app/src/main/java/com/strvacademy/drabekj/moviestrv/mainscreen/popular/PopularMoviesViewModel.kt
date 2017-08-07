package com.strvacademy.drabekj.moviestrv.mainscreen.popular

import com.strvacademy.drabekj.moviestrv.mainscreen.MainViewModel
import org.alfonz.view.StatefulLayout


class PopularMoviesViewModel: MainViewModel() {

	override fun loadData() {
		// show progress
		state.set(StatefulLayout.PROGRESS)

		// load data from data provider...
		onLoadData(dataSource.getPopularMovies())
	}
}