package com.strvacademy.drabekj.moviestrv.model.entity

import com.google.gson.annotations.SerializedName


class RequestTokenEntity {
	@SerializedName("success")
	var success: Boolean? = null

	@SerializedName("expires_at")
	var expiresAt: String? = null

	@SerializedName("request_token")
	var requestToken: String? = null
}