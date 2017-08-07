package com.strvacademy.drabekj.moviestrv.mainscreen.moviesPage

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.strvacademy.drabekj.moviestrv.mainscreen.moviesPage.nowPlaying.NowPlayingMoviesFragment
import com.strvacademy.drabekj.moviestrv.mainscreen.moviesPage.popular.PopularMoviesFragment


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
}