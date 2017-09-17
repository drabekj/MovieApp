package com.strvacademy.drabekj.moviestrv.ui.actors

import android.database.MatrixCursor
import com.strvacademy.drabekj.moviestrv.model.entity.ActorEntity
import com.strvacademy.drabekj.moviestrv.utils.basecomponents.BaseView


interface ActorsView: BaseView {
	fun onActorClick(actor: ActorEntity)
	fun showActorResults(cursor: MatrixCursor)
}