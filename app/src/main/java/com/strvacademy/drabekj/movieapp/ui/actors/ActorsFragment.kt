package com.strvacademy.drabekj.movieapp.ui.actors

import android.app.SearchManager
import android.content.Context
import android.database.MatrixCursor
import android.os.Bundle
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.inputmethod.InputMethodManager
import com.strvacademy.drabekj.movieapp.R
import com.strvacademy.drabekj.movieapp.ui.actordetail.ActorDetailActivity
import com.strvacademy.drabekj.movieapp.databinding.FragmentActorsBinding
import com.strvacademy.drabekj.movieapp.model.entity.ActorEntity
import com.strvacademy.drabekj.movieapp.ui.movies.SearchActorResultsAdapter
import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseFragment
import org.alfonz.mvvm.AlfonzActivity


class ActorsFragment: BaseFragment<ActorsView, ActorsViewModel, FragmentActorsBinding>(), ActorsView {
	var searchView: SearchView? = null


	override fun getViewModelClass(): Class<ActorsViewModel> {
		return ActorsViewModel::class.java
	}

	override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentActorsBinding {
		return FragmentActorsBinding.inflate(inflater!!)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		setupToolbar()
	}

	override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
		inflater!!.inflate(R.menu.toolbar_main, menu)
		super.onCreateOptionsMenu(menu, inflater)

		setupSearchView(menu)
	}

	private fun setupToolbar() {
		(activity as AlfonzActivity).setupActionBar(AlfonzActivity.INDICATOR_NONE, getString(R.string.actorsToolbarTitle))
		setHasOptionsMenu(true)
	}

	override fun onActorClick(actor: ActorEntity) {
		if (actor.id != null)
			ActorDetailActivity.startAsIntent(activity, actor.id!!)
	}

	private fun setupSearchView(menu: Menu?) {
		val searchManager = activity.getSystemService(Context.SEARCH_SERVICE) as SearchManager
		val searchItem = menu!!.findItem(R.id.action_search)
		searchView = searchItem.actionView as SearchView

		searchView?.setSearchableInfo(searchManager.getSearchableInfo(activity.componentName))
		val adapter = viewModel.createSearchAdapter()
		searchView?.suggestionsAdapter = adapter

		searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
			override fun onQueryTextSubmit(query: String): Boolean {
				// hide keyboard
				val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
				inputManager.hideSoftInputFromWindow(activity.currentFocus.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

				return true
			}

			override fun onQueryTextChange(newText: String): Boolean {
				viewModel.loadData(newText)
				return true
			}
		})
		searchView?.setOnSuggestionListener(object : SearchView.OnSuggestionListener {
			override fun onSuggestionSelect(position: Int): Boolean { return false }

			override fun onSuggestionClick(position: Int): Boolean {
				val searchCursor = adapter.cursor
				if (searchCursor.moveToPosition(position)) {
					val id = searchCursor.getInt(searchCursor.getColumnIndex(SearchActorResultsAdapter.RESULT_COLUMN_ID))
					ActorDetailActivity.startAsIntent(activity, id)
				}

				return true
			}
		})
	}

	override fun showActorResults(cursor: MatrixCursor) {
		searchView?.suggestionsAdapter?.swapCursor(cursor)
	}

	companion object {
		val TAG = "actors_fragment"

		fun newInstance(): ActorsFragment {
			return ActorsFragment()
		}
	}
}