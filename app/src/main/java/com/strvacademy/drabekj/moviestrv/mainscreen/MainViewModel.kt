package com.strvacademy.drabekj.moviestrv.mainscreen

import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.utils.BaseViewModel


class MainViewModel: BaseViewModel<MainView>() {
	val state = ObservableField<Int>()
}