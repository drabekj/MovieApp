package com.strvacademy.drabekj.moviestrv.utils.basecomponents

import org.alfonz.mvvm.AlfonzViewModel


abstract class BaseViewModel<V: BaseView>: AlfonzViewModel<V>() {
    fun handleError(message: String) {
        if (view != null)
            view!!.showToast(message)
    }
}