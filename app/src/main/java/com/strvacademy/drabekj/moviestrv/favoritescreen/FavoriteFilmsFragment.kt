package com.strvacademy.drabekj.moviestrv.favoritescreen

import android.view.LayoutInflater
import com.strvacademy.drabekj.moviestrv.databinding.FragmentFavoriteFilmsBinding
import com.strvacademy.drabekj.moviestrv.utils.BaseFragment

class FavoriteFilmsFragment: BaseFragment<FavoriteFilmsView, FavoriteFilmsViewModel, FragmentFavoriteFilmsBinding>(),
		FavoriteFilmsView {

	override fun getViewModelClass(): Class<FavoriteFilmsViewModel> {
		return FavoriteFilmsViewModel::class.java
	}

	override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentFavoriteFilmsBinding {
		return FragmentFavoriteFilmsBinding.inflate(inflater!!)
	}
}