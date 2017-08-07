package com.strvacademy.drabekj.moviestrv.actorsscreen

import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.databinding.FragmentActorsListItemBinding
import org.alfonz.adapter.SimpleDataBoundRecyclerAdapter


class ActorsActivityAdapter: SimpleDataBoundRecyclerAdapter<FragmentActorsListItemBinding> {

    constructor(view: ActorsView, viewModel: ActorsViewModel) :
            super(R.layout.fragment_actors_list_item, view, viewModel.actors)
}