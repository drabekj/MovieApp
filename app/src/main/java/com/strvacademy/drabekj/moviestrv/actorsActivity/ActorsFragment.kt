package com.strvacademy.drabekj.moviestrv.actorsActivity

import android.view.LayoutInflater
import com.strvacademy.drabekj.moviestrv.databinding.FragmentActorsBinding
import com.strvacademy.drabekj.moviestrv.utils.BaseFragment


class ActorsFragment: BaseFragment<ActorsView, ActorsViewModel, FragmentActorsBinding>(), ActorsView {

	override fun getViewModelClass(): Class<ActorsViewModel> {
		return ActorsViewModel::class.java
	}

	override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentActorsBinding {
		return FragmentActorsBinding.inflate(inflater!!)
	}

	override fun onActorClick(int: Int) {
		TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
	}
}