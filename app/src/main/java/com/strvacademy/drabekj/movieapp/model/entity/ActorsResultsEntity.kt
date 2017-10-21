package com.strvacademy.drabekj.movieapp.model.entity

import com.google.gson.annotations.SerializedName

class ActorsResultsEntity {

	@SerializedName("results")
	var results: Array<ActorEntity>? = null

}