package com.strvacademy.drabekj.moviestrv.model.entity

import com.google.gson.annotations.SerializedName


class SessionResponseEntity {
	@SerializedName("success")
	var success: Boolean? = null

	@SerializedName("session_id")
	var sessionId: String? = null
}