package com.strvacademy.drabekj.moviestrv.actordetailscreen

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.widget.Toast
import com.strvacademy.drabekj.moviestrv.R
import com.strvacademy.drabekj.moviestrv.databinding.FragmentActorDetailBinding
import com.strvacademy.drabekj.moviestrv.utils.BaseFragment
import org.alfonz.mvvm.AlfonzActivity

class ActorDetailFragment: BaseFragment<ActorDetailView, ActorDetailViewModel, FragmentActorDetailBinding>(), ActorDetailView {

	override fun getViewModelClass(): Class<ActorDetailViewModel> {
		return ActorDetailViewModel::class.java
	}

	override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentActorDetailBinding {
		return FragmentActorDetailBinding.inflate(inflater!!)
	}

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		setupToolbar()
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		if (arguments != null)
			viewModel.id = arguments.getInt(ARG_ACTOR_ID)
	}

	override fun onAllCreditsClick() {
		showToast("Show all credits")
	}

	private fun setupToolbar() {
		(activity as AlfonzActivity).setupActionBar(AlfonzActivity.INDICATOR_BACK, "")
		val upArrow: Drawable = resources.getDrawable(R.drawable.abc_ic_ab_back_material)
		(activity as AlfonzActivity).supportActionBar!!.setHomeAsUpIndicator(upArrow)

		setHasOptionsMenu(true)
	}

	companion object {
		private val ARG_ACTOR_ID = "param_actor_id"

		fun newInstance(param: Int): ActorDetailFragment {
			val fragment = ActorDetailFragment()
			val args = Bundle()
			args.putInt(ARG_ACTOR_ID, param)
			fragment.arguments = args
			return fragment
		}
	}
}