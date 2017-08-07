package com.strvacademy.drabekj.moviestrv.mainscreen.moviesPage

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.databinding.FragmentMoviesPageBinding
import com.strvacademy.drabekj.moviestrv.moviedetailscreen.MovieDetailActivity
import com.strvacademy.drabekj.moviestrv.model.Movie
import com.strvacademy.drabekj.moviestrv.utils.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*
import org.alfonz.mvvm.AlfonzActivity


abstract class MoviesPageFragment<VM : MoviesPageViewModel> : BaseFragment<MoviesPageView, VM, FragmentMoviesPageBinding>(), MoviesPageView {
	private var mAdapter: MoviesPageFragmentAdapter? = null


	companion object { val EXTRA_KEY_MOVIE_ID = "EXTRA_KEY_MOVIE_ID" }


	override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentMoviesPageBinding {
		return FragmentMoviesPageBinding.inflate(inflater!!)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		setupAdapter()
	}

	override fun onItemClick(movie: Movie) {
		startMovieDetailActivity(movie.id)
	}

	private fun startMovieDetailActivity(id: Int) {
		val intent = Intent(activity, MovieDetailActivity::class.java)
		intent.putExtra(EXTRA_KEY_MOVIE_ID, id)
		startActivity(intent)
	}

	private fun setupAdapter() {
		if (mAdapter == null) {
			mAdapter = MoviesPageFragmentAdapter(this, viewModel)
			binding.fragmentMainListRecycler.adapter = mAdapter
		}
	}


	//	TODO ! refactoring
	//	override fun onBackPressed() {
	//		if (pager.currentItem == 0) {
	//			// If the user is currently looking at the first step, allow the system to handle the
	//			// Back button. This calls finish() on this activity and pops the back stack.
	//			super.onBackPressed()
	//		} else {
	//			// Otherwise, select the previous step.
	//			pager.currentItem = pager.currentItem - 1
	//		}
	//	}
}