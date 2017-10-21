package com.strvacademy.drabekj.movieapp.ui.movies.moviesPage

import com.strvacademy.drabekj.movieapp.R
import com.strvacademy.drabekj.movieapp.databinding.FragmentMoviesPageListItemBinding
import org.alfonz.adapter.SimpleDataBoundRecyclerAdapter


class MoviesPageFragmentAdapter : SimpleDataBoundRecyclerAdapter<FragmentMoviesPageListItemBinding> {

    constructor(view: MoviesPageView, viewModel: MoviesPageViewModel) :
            super(R.layout.fragment_movies_page_list_item, view, viewModel.movies)
}