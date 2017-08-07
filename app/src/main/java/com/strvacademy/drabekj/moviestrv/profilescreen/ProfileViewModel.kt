package com.strvacademy.drabekj.moviestrv.profilescreen

import android.databinding.ObservableField
import com.strvacademy.drabekj.moviestrv.utils.BaseViewModel

class ProfileViewModel: BaseViewModel<ProfileView>() {
	val state = ObservableField<Int>()
}