package com.strvacademy.drabekj.moviestrv.mainscreen.nowPlaying

import com.strvacademy.drabekj.moviestrv.mainscreen.MainViewModel
import org.alfonz.view.StatefulLayout


class NowPlayingMoviesViewModel : MainViewModel() {

	override fun loadData() {
		// show progress
		state.set(StatefulLayout.PROGRESS)

		// load data from data provider...
		onLoadData(dataSource.getNowPlayingMovies())
	}
}