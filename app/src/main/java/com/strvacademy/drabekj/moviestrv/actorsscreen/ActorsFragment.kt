package com.strvacademy.drabekj.moviestrv.actorsscreen

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.strvacademy.drabekj.moviestrv.MoviesApplication
import com.strvacademy.drabekj.moviestrv.actordetailscreen.ActorDetailActivity
import com.strvacademy.drabekj.moviestrv.databinding.FragmentActorsBinding
import com.strvacademy.drabekj.moviestrv.model.Actor
import com.strvacademy.drabekj.moviestrv.utils.BaseFragment


class ActorsFragment: BaseFragment<ActorsView, ActorsViewModel, FragmentActorsBinding>(), ActorsView {
	private var mAdapter: ActorsActivityAdapter? = null

	companion object { val EXTRA_KEY_ACTOR_ID = "EXTRA_KEY_ACTOR_ID" }


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
		startActorDetailActivity(actor.id)
	}

	private fun startActorDetailActivity(id: Int) {
		val intent = Intent(activity, ActorDetailActivity::class.java)
		intent.putExtra(EXTRA_KEY_ACTOR_ID, id)
		startActivity(intent)
	}

	private fun setupAdapter() {
		if (mAdapter == null) {
			mAdapter = ActorsActivityAdapter(this, viewModel)
			binding.fragmentActorsListRecycler.adapter = mAdapter
		}
	}
}