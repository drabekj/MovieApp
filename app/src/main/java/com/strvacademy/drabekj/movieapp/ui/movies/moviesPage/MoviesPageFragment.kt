package com.strvacademy.drabekj.movieapp.ui.movies.moviesPage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.strvacademy.drabekj.movieapp.databinding.FragmentMoviesPageBinding
import com.strvacademy.drabekj.movieapp.ui.moviedetail.MovieDetailActivity
import com.strvacademy.drabekj.movieapp.model.entity.MovieEntity
import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseFragment
import org.alfonz.utility.Logcat


abstract class MoviesPageFragment<VM : MoviesPageViewModel> : BaseFragment<MoviesPageView, VM, FragmentMoviesPageBinding>(), MoviesPageView {
	private var mAdapter: MoviesPageFragmentAdapter? = null


	companion object {
		val EXTRA_KEY_MOVIE_ID = "EXTRA_KEY_MOVIE_ID"
	}


	override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentMoviesPageBinding {
		return FragmentMoviesPageBinding.inflate(inflater!!)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		setupAdapter()
	}

	override fun onItemClick(movie: MovieEntity) {
		Logcat.d("DEBUG - movie id:" + movie.id!!)
		startMovieDetailActivity(movie.id!!)
	}

	private fun startMovieDetailActivity(id: Int) {
		val intent = Intent(activity, MovieDetailActivity::class.java)
		intent.putExtra(EXTRA_KEY_MOVIE_ID, id)
		startActivity(intent)
	}

	private fun setupAdapter() {
		mAdapter = MoviesPageFragmentAdapter(this, viewModel)
		binding.fragmentMainListRecycler.adapter = mAdapter
	}
}