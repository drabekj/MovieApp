package com.strvacademy.drabekj.moviestrv.mainscreen.moviesPage.nowPlaying

import com.strvacademy.drabekj.moviestrv.mainscreen.moviesPage.MoviesPageFragment


class NowPlayingMoviesFragment: MoviesPageFragment<NowPlayingMoviesViewModel>() {
	override fun getViewModelClass(): Class<NowPlayingMoviesViewModel> {
		return NowPlayingMoviesViewModel::class.java
	}


	companion object {
		fun newInstance(): NowPlayingMoviesFragment {
			return NowPlayingMoviesFragment()
		}
	}
}