package com.strvacademy.drabekj.moviestrv.mainscreen.moviesPage

import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.databinding.FragmentMainListItemBinding
import com.strvacademy.drabekj.moviestrv.mainscreen.moviesPage.MoviesPageView
import com.strvacademy.drabekj.moviestrv.mainscreen.moviesPage.MoviesPageViewModel
import org.alfonz.adapter.SimpleDataBoundRecyclerAdapter


class MoviesPageFragmentAdapter : SimpleDataBoundRecyclerAdapter<FragmentMainListItemBinding> {

    constructor(view: MoviesPageView, viewModel: MoviesPageViewModel) :
            super(R.layout.fragment_main_list_item, view, viewModel.movies)
}