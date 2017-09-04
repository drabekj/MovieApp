package com.strvacademy.drabekj.moviestrv.model.entity

import com.google.gson.annotations.SerializedName

class ActorsResultsEntity {

	@SerializedName("results")
	var results: Array<ActorEntity>? = null

}