package com.strvacademy.drabekj.moviestrv.mainActivity.nowPlaying

import com.strvacademy.drabekj.moviestrv.mainActivity.MainViewModel
import org.alfonz.view.StatefulLayout


class NowPlayingMoviesViewModel : MainViewModel() {

	override fun loadData() {
		// show progress
		state.set(StatefulLayout.PROGRESS)

		// load data from data provider...
		onLoadData(dataSource.getNowPlayingMovies())
	}
}