package com.strvacademy.drabekj.moviestrv.mainscreen.moviesPage.nowPlaying

import com.strvacademy.drabekj.moviestrv.mainscreen.moviesPage.MoviesPageViewModel
import org.alfonz.view.StatefulLayout


class NowPlayingMoviesViewModel : MoviesPageViewModel() {

	override fun loadData() {
		// show progress
		state.set(StatefulLayout.PROGRESS)

		// load data from data provider...
		onLoadData(dataSource.getNowPlayingMovies())
	}
}