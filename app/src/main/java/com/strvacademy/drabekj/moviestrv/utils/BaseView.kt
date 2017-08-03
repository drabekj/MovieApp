package com.strvacademy.drabekj.moviestrv.utils

import org.alfonz.mvvm.AlfonzView


interface BaseView: AlfonzView {
    fun showToast(message: String)
}