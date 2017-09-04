package com.strvacademy.drabekj.moviestrv.ui.actors

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.ui.actordetail.ActorDetailActivity
import com.strvacademy.drabekj.moviestrv.databinding.FragmentActorsBinding
import com.strvacademy.drabekj.moviestrv.model.Actor
import com.strvacademy.drabekj.moviestrv.model.entity.ActorEntity
import com.strvacademy.drabekj.moviestrv.utils.BaseFragment
import org.alfonz.mvvm.AlfonzActivity


class ActorsFragment: BaseFragment<ActorsView, ActorsViewModel, FragmentActorsBinding>(), ActorsView {

	override fun getViewModelClass(): Class<ActorsViewModel> {
		return ActorsViewModel::class.java
	}

	override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentActorsBinding {
		return FragmentActorsBinding.inflate(inflater!!)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)
		setupToolbar()
	}

	override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
		inflater!!.inflate(R.menu.toolbar_main, menu)
		super.onCreateOptionsMenu(menu, inflater)
	}

	override fun onOptionsItemSelected(item: MenuItem?): Boolean {
		when (item!!.itemId) {
			R.id.action_search -> {
				showToast("Search Action")
				return true
			}

			else -> return super.onOptionsItemSelected(item)
		}
	}

	private fun setupToolbar() {
		(activity as AlfonzActivity).setupActionBar(AlfonzActivity.INDICATOR_NONE, getString(R.string.actorsToolbarTitle))
		setHasOptionsMenu(true)
	}

	override fun onActorClick(actor: ActorEntity) {
		startActorDetailActivity(actor.id!!)
	}

	private fun startActorDetailActivity(id: Int) {
		val intent = Intent(activity, ActorDetailActivity::class.java)
		intent.putExtra(EXTRA_KEY_ACTOR_ID, id)
		startActivity(intent)
	}

	companion object {
		val EXTRA_KEY_ACTOR_ID = "EXTRA_KEY_ACTOR_ID"

		fun newInstance(): ActorsFragment {
			return ActorsFragment()
		}
	}
}