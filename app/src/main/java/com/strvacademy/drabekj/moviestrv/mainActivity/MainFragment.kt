package com.strvacademy.drabekj.moviestrv.mainActivity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import com.strvacademy.drabekj.moviestrv.databinding.FragmentMainBinding
import com.strvacademy.drabekj.moviestrv.detailActivity.MovieDetailActivity
import com.strvacademy.drabekj.moviestrv.model.Movie
import com.strvacademy.drabekj.moviestrv.utils.BaseFragment


abstract class MainFragment<VM: MainViewModel>: BaseFragment<MainView, VM, FragmentMainBinding>(), MainView {
    private var mAdapter: MainActivityAdapter? = null

    companion object { val EXTRA_KEY_MOVIE_ID = "EXTRA_KEY_MOVIE_ID" }


    override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater!!)
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
            mAdapter = MainActivityAdapter(this, viewModel)
            binding.fragmentMainListRecycler.adapter = mAdapter
        }
    }
}