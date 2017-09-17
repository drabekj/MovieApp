package com.strvacademy.drabekj.moviestrv.ui.moviedetail

import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseView


interface MovieDetailView: BaseView {
    fun onFullCastClick()
    fun startActorDetailActivity(actorId: Int)
}