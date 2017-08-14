package com.strvacademy.drabekj.moviestrv.ui.profile

import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.databinding.FragmentProfileListItemBinding
import org.alfonz.adapter.SimpleDataBoundRecyclerAdapter


class ProfileFragmentAdapter: SimpleDataBoundRecyclerAdapter<FragmentProfileListItemBinding> {

    constructor(view: ProfileView, viewModel: ProfileViewModel) :
            super(R.layout.fragment_profile_list_item, view, viewModel.favMovies)
}