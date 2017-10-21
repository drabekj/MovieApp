package com.strvacademy.drabekj.movieapp.ui.actors

import android.database.MatrixCursor
import com.strvacademy.drabekj.movieapp.model.entity.ActorEntity
import com.strvacademy.drabekj.movieapp.utils.basecomponents.BaseView


interface ActorsView: BaseView {
	fun onActorClick(actor: ActorEntity)
	fun showActorResults(cursor: MatrixCursor)
}