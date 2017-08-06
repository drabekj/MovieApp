package com.strvacademy.drabekj.moviestrv.actorsActivity

import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.databinding.FragmentActorsListItemBinding
import com.strvacademy.drabekj.moviestrv.databinding.FragmentMainListItemBinding
import com.strvacademy.drabekj.moviestrv.mainActivity.MainView
import com.strvacademy.drabekj.moviestrv.mainActivity.MainViewModel
import org.alfonz.adapter.SimpleDataBoundRecyclerAdapter


class ActorsActivityAdapter: SimpleDataBoundRecyclerAdapter<FragmentActorsListItemBinding> {

    constructor(view: ActorsView, viewModel: ActorsViewModel) :
            super(R.layout.fragment_actors_list_item, view, viewModel.actors)
}