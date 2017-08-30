package com.strvacademy.drabekj.moviestrv.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CastEntity {

	@SerializedName("id")
	var id: Int? = null

	@SerializedName("name")
	var name: String? = null

	@SerializedName("character")
	var character: String? = null

	@SerializedName("profile_path")
	var profilePath: Any? = null

	@SerializedName("cast_id")
	var castId: Int? = null

	@SerializedName("credit_id")
	var creditId: String? = null

	@SerializedName("gender")
	var gender: Int? = null

	// Position in the List when showing cast (starting with 0)
	@SerializedName("order")
	var order: Int? = null

}