package com.strvacademy.drabekj.moviestrv.mainActivity

import android.view.LayoutInflater
import com.strvacademy.drabekj.moviestrv.databinding.FragmentMainBinding
import com.strvacademy.drabekj.moviestrv.utils.BaseFragment


class MainFragment: BaseFragment<MainView, MainViewModel, FragmentMainBinding>(), MainView {

    override fun getViewModelClass(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun inflateBindingLayout(inflater: LayoutInflater?): FragmentMainBinding {
        return FragmentMainBinding.inflate(inflater!!)
    }

    override fun onClick() {
        viewModel.updateMessage()
    }
}