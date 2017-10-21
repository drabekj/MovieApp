package com.strvacademy.drabekj.movieapp.model.entity

import com.google.gson.annotations.SerializedName


class ErrorEntity {

	@SerializedName("status_code")
	var statusCode: Int? = null

	@SerializedName("status_message")
	var statusMessage: String? = null

}