package com.strvacademy.drabekj.moviestrv.mainscreen.nowPlaying

import com.strvacademy.drabekj.moviestrv.mainscreen.MainFragment


class NowPlayingMoviesFragment: MainFragment<NowPlayingMoviesViewModel>() {
	override fun getViewModelClass(): Class<NowPlayingMoviesViewModel> {
		return NowPlayingMoviesViewModel::class.java
	}
}