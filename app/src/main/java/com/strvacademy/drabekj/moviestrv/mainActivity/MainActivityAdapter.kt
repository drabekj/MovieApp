package com.strvacademy.drabekj.moviestrv.mainActivity

import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.databinding.FragmentMainListItemBinding
import org.alfonz.adapter.SimpleDataBoundRecyclerAdapter


class MainActivityAdapter: SimpleDataBoundRecyclerAdapter<FragmentMainListItemBinding> {

    constructor(view: MainView, viewModel: MainViewModel) :
            super(R.layout.fragment_main_list_item, view, viewModel.movies)
}