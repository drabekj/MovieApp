package com.strvacademy.drabekj.moviestrv.moviedetailscreen

import android.os.Bundle
import android.view.LayoutInflater
import com.strvacademy.drabekj.moviestrv.databinding.FragmentMovieDetailBinding
import com.strvacademy.drabekj.moviestrv.utils.BaseFragment


class MovieDetailFragment: BaseFragment<MovieDetailView, MovieDetailViewModel, FragmentMovieDetailBinding>(),
        MovieDetailView {

    override fun getViewModelClass(): Class<MovieDetailViewModel> {
        return MovieDetailViewModel::class.java
    }

    override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentMovieDetailBinding {
        return FragmentMovieDetailBinding.inflate(inflater!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null)
            viewModel.id = arguments.getInt(ARG_MOVIE_ID)
    }

    override fun onFullCastClick() {
        showToast("Show Full Cast")
    }


    companion object {
        private val ARG_MOVIE_ID = "param_movie_id"

        fun newInstance(param: Int): MovieDetailFragment {
            val fragment = MovieDetailFragment()
            val args = Bundle()
            args.putInt(ARG_MOVIE_ID, param)
            fragment.arguments = args
            return fragment
        }
    }
}