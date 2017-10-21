package com.strvacademy.drabekj.movieapp.model.entity

import com.google.gson.annotations.SerializedName

class CreditsEntity {

	@SerializedName("cast")
	var cast: Array<CastEntity>? = null

	@SerializedName("crew")
	var crew: Array<CrewEntity>? = null

}