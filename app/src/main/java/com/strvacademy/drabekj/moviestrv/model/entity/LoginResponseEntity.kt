package com.strvacademy.drabekj.moviestrv.model.entity

import com.google.gson.annotations.SerializedName


class LoginResponseEntity {
	@SerializedName("success")
	var success: Boolean? = null

	@SerializedName("request_token")
	var requestToken: String? = null
}