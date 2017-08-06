package com.strvacademy.drabekj.moviestrv.actorsActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.databinding.FragmentActorsBinding
import com.strvacademy.drabekj.moviestrv.model.Actor
import com.strvacademy.drabekj.moviestrv.utils.BaseFragment


class ActorsFragment: BaseFragment<ActorsView, ActorsViewModel, FragmentActorsBinding>(), ActorsView {
	private var mAdapter: ActorsActivityAdapter? = null


	override fun getViewModelClass(): Class<ActorsViewModel> {
		return ActorsViewModel::class.java
	}

	override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentActorsBinding {
		return FragmentActorsBinding.inflate(inflater!!)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		setupAdapter()
	}

	override fun onActorClick(actor: Actor) {
		Toast.makeText(MoviesApplication.getContext(), "Click " + actor.id, Toast.LENGTH_SHORT).show()
	}

	private fun setupAdapter() {
		if (mAdapter == null) {
			mAdapter = ActorsActivityAdapter(this, viewModel)
			binding.fragmentActorsListRecycler.adapter = mAdapter
		}
	}
}