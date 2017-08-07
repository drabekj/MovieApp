package com.strvacademy.drabekj.moviestrv.moviesscreen.moviesPage

import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.databinding.FragmentMoviesPageListItemBinding
import org.alfonz.adapter.SimpleDataBoundRecyclerAdapter


class MoviesPageFragmentAdapter : SimpleDataBoundRecyclerAdapter<FragmentMoviesPageListItemBinding> {

    constructor(view: MoviesPageView, viewModel: MoviesPageViewModel) :
            super(R.layout.fragment_movies_page_list_item, view, viewModel.movies)
}