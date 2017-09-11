package com.strvacademy.drabekj.moviestrv.ui.movies

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.databinding.FragmentMoviesBinding
import com.strvacademy.drabekj.moviestrv.ui.movies.moviesPage.ScreenSlidePagerAdapter
import com.strvacademy.drabekj.moviestrv.utils.BaseFragment
import kotlinx.android.synthetic.main.fragment_movies.*
import org.alfonz.mvvm.AlfonzActivity


class MoviesFragment : BaseFragment<MoviesView, MoviesViewModel, FragmentMoviesBinding>(), MoviesView {

	override fun getViewModelClass(): Class<MoviesViewModel> {
		return MoviesViewModel::class.java
	}

	override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentMoviesBinding {
		return FragmentMoviesBinding.inflate(inflater!!)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		setupToolbar()
		setupViewPager()
	}

	override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
		inflater!!.inflate(R.menu.toolbar_main, menu)
		super.onCreateOptionsMenu(menu, inflater)
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		when (item!!.itemId) {
			R.id.action_search -> {
				showToast("Search Action")
				return true
			}

			else -> return super.onOptionsItemSelected(item)
		}
	}

	private fun setupToolbar() {
		(activity as AlfonzActivity).setupActionBar(AlfonzActivity.INDICATOR_NONE, getString(R.string.moviesToolbarTitle))
		setHasOptionsMenu(true)
	}

	private fun setupViewPager() {
		// Instantiate a ViewPager and a PagerAdapter.
		val mPagerAdapter = ScreenSlidePagerAdapter(childFragmentManager)
		pager.adapter = mPagerAdapter
		tab_layout.setupWithViewPager(pager)
	}


	companion object {
		val TAG = "movies_fragment"

		fun newInstance(): MoviesFragment {
			return MoviesFragment()
		}
	}
}