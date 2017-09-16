package com.strvacademy.drabekj.moviestrv.ui.movies

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.database.MatrixCursor
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
import android.view.LayoutInflater
import com.strvacademy.drabekj.moviestrv.ui.moviedetail.MovieDetailActivity
import com.strvacademy.drabekj.moviestrv.ui.movies.moviesPage.MoviesPageFragment


class MoviesFragment : BaseFragment<MoviesView, MoviesViewModel, FragmentMoviesBinding>(), MoviesView {
	var searchView: SearchView? = null


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

		setupSearchView(menu)
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

	private fun setupSearchView(menu: Menu?) {
		val searchManager = activity.getSystemService(Context.SEARCH_SERVICE) as SearchManager
		val searchItem = menu!!.findItem(R.id.action_search)
		searchView = searchItem.actionView as SearchView

		searchView?.setSearchableInfo(searchManager.getSearchableInfo(activity.componentName))
		val adapter = viewModel.createSearchAdapter()
		searchView?.suggestionsAdapter = adapter
		searchView?.isSubmitButtonEnabled = true

		searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(query: String): Boolean {
				Logcat.d("query submit: " + query)
				searchView?.clearFocus()
				return true
			}

			override fun onQueryTextChange(newText: String): Boolean {
				Logcat.d("query changed: " + newText)
				viewModel.loadData(newText)
				return true
			}
		})
		searchView?.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
			override fun onSuggestionSelect(position: Int): Boolean { return false }

			override fun onSuggestionClick(position: Int): Boolean {
				val searchCursor = adapter.cursor
				if (searchCursor.moveToPosition(position)) {
					val id = searchCursor.getInt(searchCursor.getColumnIndex(SearchResultAdapter.RESULT_COLUMN_ID))
					startMovieDetailActivity(id)
				}

				return true
			}
		})
	}

	override fun showMovieResults(cursor: MatrixCursor) {
		searchView?.suggestionsAdapter?.swapCursor(cursor)
	}

	private fun startMovieDetailActivity(id: Int) {
		val intent = Intent(activity, MovieDetailActivity::class.java)
		intent.putExtra(MoviesPageFragment.EXTRA_KEY_MOVIE_ID, id)
		startActivity(intent)
	}

	companion object {
		val TAG = "movies_fragment"

		fun newInstance(): MoviesFragment {
			return MoviesFragment()
		}
	}
}