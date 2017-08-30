package com.strvacademy.drabekj.moviestrv.model.remote.rest

import com.strvacademy.drabekj.moviestrv.model.entity.ErrorEntity
import org.alfonz.rest.HttpException
import retrofit2.Response
import java.io.IOException

class RestHttpException(response: Response<*>) : HttpException(response) {


	override fun parseError(response: Response<*>): Any {
		try {
			val converter = RetrofitClient.retrofit.responseBodyConverter<Error>(ErrorEntity::class.java, arrayOfNulls<Annotation>(0))
			return converter.convert(response.errorBody()!!)
		} catch (e: IOException) {
			e.printStackTrace()
			val error = ErrorEntity()
			error.statusMessage = "Unknown error"
			return error
		} catch (e: NullPointerException) {
			e.printStackTrace()
			val error = ErrorEntity()
			error.statusMessage = "Unknown error"
			return error
		}

	}
}