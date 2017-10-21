package com.strvacademy.drabekj.movieapp.ui.moviedetail

import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseView


interface MovieDetailView: BaseView {
    fun onFullCastClick()
    fun startActorDetailActivity(actorId: Int)
    fun initializeYouTubePlayer(url_key: String)
    fun showNeedToBeLoggedInToast()
}