package com.strvacademy.drabekj.moviestrv


object MoviesConfig {
	const val LOGS = BuildConfig.LOGS
	const val DEV_ENVIRONMENT = BuildConfig.DEV_ENVIRONMENT

	const val YOUTUBE_API_KEY = "AIzaSyA_0SeHJh9m5A5P0n3aiZoqownhpyQANGo"

	const val REST_BASE_URL_PROD = "https://api.themoviedb.org/3/"
	const val REST_BASE_URL_DEV = "https://api.themoviedb.org/3/"
	val REST_BASE_URL = if (DEV_ENVIRONMENT) REST_BASE_URL_DEV else REST_BASE_URL_PROD
}