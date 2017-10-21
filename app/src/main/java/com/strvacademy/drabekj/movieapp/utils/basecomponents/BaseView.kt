package com.strvacademy.drabekj.movieapp.utils.basecomponents

import org.alfonz.mvvm.AlfonzView


interface BaseView: AlfonzView {
    fun showToast(message: String)
}