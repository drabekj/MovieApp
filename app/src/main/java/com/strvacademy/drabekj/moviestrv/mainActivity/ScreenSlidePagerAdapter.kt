package com.strvacademy.drabekj.moviestrv.mainActivity

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter


class ScreenSlidePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {
    private val NUM_PAGES = 2


    override fun getItem(position: Int): Fragment {
        when (position) {

            0 -> return MainFragment()

            1 -> return MainFragment()

            else -> return MainFragment()
        }
    }

    override fun getCount(): Int {
        return NUM_PAGES
    }
}