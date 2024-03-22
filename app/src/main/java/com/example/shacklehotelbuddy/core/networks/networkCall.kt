package com.example.shacklehotelbuddy.core.networks

import com.example.shacklehotelbuddy.core.functional.Either
import com.example.shacklehotelbuddy.core.functional.Failure
import retrofit2.Response
import timber.log.Timber

/**
 * A utility class that converts retrofit responses to Either*/
suspend fun <ResponseType> networkCall(call: suspend () -> Response<ResponseType>): Either<Failure, ResponseType> {
  try {
    val response = call()
    return if (!response.isSuccessful) {
      when (response.code()) {
        401 -> Either.Left(Failure.AuthError)
        403 -> Either.Left(Failure.Forbidden)
        400 -> Either.Left(Failure.BadRequest)
        404 -> Either.Left(Failure.NotFound)
        415 -> Either.Left(Failure.UnSupportedMediaType)
        500 -> Either.Left(Failure.InternalServerError)
        else -> Either.Left(Failure.ServerError)
      }
    } else {
      Either.Right(response.body()!!)
    }
  } catch (exception: Exception) {
    Timber.d("exception ${exception}")
    return Either.Left(Failure.NetworkConnection)
  }

}