package com.strvacademy.drabekj.movieapp.model.entity

import com.google.gson.annotations.SerializedName


class SetFavouriteResponseEntity {

	@SerializedName("status_code")
	var statusCode: Int? = null

	@SerializedName("status_message")
	var statusMessage: String? = null

}