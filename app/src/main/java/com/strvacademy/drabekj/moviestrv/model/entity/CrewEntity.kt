package com.strvacademy.drabekj.moviestrv.model.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class CrewEntity {

	@SerializedName("id")
	var id: Int? = null

	@SerializedName("name")
	var name: String? = null

	@SerializedName("job")
	var job: String? = null

	@SerializedName("department")
	var department: String? = null

	@SerializedName("profile_path")
	var profilePath: Any? = null

	@SerializedName("credit_id")
	var creditId: String? = null

	@SerializedName("gender")
	var gender: Int? = null

}