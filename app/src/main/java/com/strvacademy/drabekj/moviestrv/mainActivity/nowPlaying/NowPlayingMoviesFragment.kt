package com.strvacademy.drabekj.moviestrv.mainActivity.nowPlaying

import com.strvacademy.drabekj.moviestrv.mainActivity.MainFragment


class NowPlayingMoviesFragment: MainFragment<NowPlayingMoviesViewModel>() {
	override fun getViewModelClass(): Class<NowPlayingMoviesViewModel> {
		return NowPlayingMoviesViewModel::class.java
	}
}