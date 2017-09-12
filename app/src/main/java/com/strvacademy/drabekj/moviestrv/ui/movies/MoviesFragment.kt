package com.strvacademy.drabekj.moviestrv.ui.movies

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.*
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.databinding.FragmentMoviesBinding
import com.strvacademy.drabekj.moviestrv.ui.movies.moviesPage.ScreenSlidePagerAdapter
import com.strvacademy.drabekj.moviestrv.utils.BaseFragment
import kotlinx.android.synthetic.main.fragment_movies.*
import org.alfonz.mvvm.AlfonzActivity
import org.alfonz.utility.Logcat


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

		val searchManager = activity.getSystemService(Context.SEARCH_SERVICE) as SearchManager
		val searchItem = menu!!.findItem(R.id.action_search)
		val searchView = searchItem.actionView as SearchView

		searchView.setSearchableInfo(searchManager.getSearchableInfo(activity.componentName))
		searchView.isSubmitButtonEnabled = true
		searchView.isSubmitButtonEnabled = true


		searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(query: String): Boolean {
				Logcat.d("query submit: " + query)

				return true
			}

			override fun onQueryTextChange(newText: String): Boolean {
				Logcat.d("query changed: " + newText)

				return true
			}
		})
		searchView.setOnClickListener { view -> showToast("click") }
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