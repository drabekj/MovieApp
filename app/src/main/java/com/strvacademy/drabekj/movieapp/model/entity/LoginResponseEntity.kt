package com.strvacademy.drabekj.movieapp.model.entity

import com.google.gson.annotations.SerializedName


class LoginResponseEntity {
	@SerializedName("success")
	var success: Boolean? = null

	@SerializedName("request_token")
	var requestToken: String? = null
}