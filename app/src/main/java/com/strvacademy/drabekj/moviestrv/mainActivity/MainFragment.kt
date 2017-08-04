package com.strvacademy.drabekj.moviestrv.mainActivity

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.databinding.FragmentMainBinding
import com.strvacademy.drabekj.moviestrv.model.Movie
import com.strvacademy.drabekj.moviestrv.utils.BaseFragment


class MainFragment: BaseFragment<MainView, MainViewModel, FragmentMainBinding>(), MainView {
    private var mAdapter: MainActivityAdapter? = null

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater!!)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupAdapter()
    }

    override fun onItemClick(movie: Movie) {
        viewModel.showToast()
//        startMovieDetailActivity(movie.id)
    }

    private fun setupAdapter() {
        if (mAdapter == null) {
            mAdapter = MainActivityAdapter(this, viewModel)
            binding.fragmentMainListRecycler.adapter = mAdapter
        }
    }
}