package com.strvacademy.drabekj.moviestrv.model.entity

import com.google.gson.annotations.SerializedName


class SetFavouriteResponseEntity {

	@SerializedName("status_code")
	var statusCode: Int? = null

	@SerializedName("status_message")
	var statusMessage: String? = null

}