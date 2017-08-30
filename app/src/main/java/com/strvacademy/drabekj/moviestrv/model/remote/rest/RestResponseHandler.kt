package com.strvacademy.drabekj.moviestrv.model.remote.rest

import com.strvacademy.drabekj.moviestrv.model.entity.ErrorEntity
import org.alfonz.rest.HttpException
import org.alfonz.rest.ResponseHandler
import retrofit2.Response

class RestResponseHandler : ResponseHandler {
	override fun isSuccess(response: Response<*>): Boolean {
		return response.isSuccessful
	}


	override fun getErrorMessage(exception: HttpException): String {
		val error = exception.error() as ErrorEntity
		return error.statusMessage!!
	}


	override fun getFailMessage(throwable: Throwable): String {
		return throwable.localizedMessage
	}


	override fun createHttpException(response: Response<*>): HttpException {
		return RestHttpException(response)
	}
}