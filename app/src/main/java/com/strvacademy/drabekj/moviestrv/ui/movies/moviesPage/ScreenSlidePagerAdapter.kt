package com.strvacademy.drabekj.moviestrv.ui.movies.moviesPage

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.strvacademy.drabekj.moviestrv.ui.movies.moviesPage.nowPlaying.NowPlayingMoviesFragment
import com.strvacademy.drabekj.moviestrv.ui.movies.moviesPage.popular.PopularMoviesFragment


class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val NUM_PAGES = 2


    override fun getItem(position: Int): Fragment {
        when (position) {

            0 -> return PopularMoviesFragment()

            1 -> return NowPlayingMoviesFragment()

            else -> return PopularMoviesFragment()
        }
    }

    override fun getCount(): Int {
        return NUM_PAGES
    }

	override fun getPageTitle(position: Int): CharSequence {
		return when (position) {

			0 -> "Popular"

			1 -> "Now Playing"

			else -> ""
		}
	}
}