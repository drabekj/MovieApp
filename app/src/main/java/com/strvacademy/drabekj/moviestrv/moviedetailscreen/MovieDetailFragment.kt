package com.strvacademy.drabekj.moviestrv.moviedetailscreen

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.databinding.FragmentMovieDetailBinding
import com.strvacademy.drabekj.moviestrv.model.Actor
import com.strvacademy.drabekj.moviestrv.utils.BaseFragment
import org.alfonz.mvvm.AlfonzActivity


class MovieDetailFragment: BaseFragment<MovieDetailView, MovieDetailViewModel, FragmentMovieDetailBinding>(),
        MovieDetailView {

    override fun getViewModelClass(): Class<MovieDetailViewModel> {
        return MovieDetailViewModel::class.java
    }

    override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentMovieDetailBinding {
        return FragmentMovieDetailBinding.inflate(inflater!!)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupToolbar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null)
            viewModel.id = arguments.getInt(ARG_MOVIE_ID)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.detail_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_favorite -> {
                showToast("Mark as favorite")
                return true
            }

            R.id.action_share -> {
                showToast("Share action")
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onFullCastClick() {
        showToast("Show Full Cast")
    }

	private fun setupToolbar() {
        (activity as AlfonzActivity).setupActionBar(AlfonzActivity.INDICATOR_BACK, "")
        setHasOptionsMenu(true)
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