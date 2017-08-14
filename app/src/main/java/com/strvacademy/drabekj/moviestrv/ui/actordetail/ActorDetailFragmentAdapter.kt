package com.strvacademy.drabekj.moviestrv.ui.actordetail

import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.databinding.FragmentActorDetailListItemBinding
import org.alfonz.adapter.SimpleDataBoundRecyclerAdapter


class ActorDetailFragmentAdapter: SimpleDataBoundRecyclerAdapter<FragmentActorDetailListItemBinding> {

    constructor(view: ActorDetailView, viewModel: ActorDetailViewModel) :
            super(R.layout.fragment_actor_detail_list_item, view, viewModel.knownForMovies)
}