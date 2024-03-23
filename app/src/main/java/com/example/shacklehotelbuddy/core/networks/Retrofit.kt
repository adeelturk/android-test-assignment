package com.example.shacklehotelbuddy.core.networks

import android.util.Log
import com.example.shacklehotelbuddy.core.functional.Either
import com.example.shacklehotelbuddy.core.networks.error.ErrorEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Call


/**
 * Takes in a transform lambda to return a modified version of the responses
 */

var generalErrorImplementation = GeneralErrorImplementation()

@Suppress("unused")
fun <T, R> Call<T>.requestBlocking(transform: (T) -> R): Either<ErrorEntity, R> {
    return try {
        val response = execute()

        when (response.isSuccessful) {
            true -> Either.Right(transform(response.body()!!))

            false -> Either.Left(generalErrorImplementation.getHttpErrors(response))
        }
    } catch (exception: Throwable) {

        Either.Left(generalErrorImplementation.getError(exception))

    }

}


fun <T> Call<T>.requestBlocking(): Either<ErrorEntity, T> {
    return try {
        val response = execute()
        when (response.isSuccessful) {
            true -> Either.Right((response.body()!!))
            false -> Either.Left(generalErrorImplementation.getHttpErrors(response))
        }
    } catch (exception: Throwable) {
        Either.Left(generalErrorImplementation.getError(exception))
    }


}


@Suppress("unused")
 fun <T, R : Any> Call<T>.requestBlockingFlow(transform: (T) -> R): Flow<Either<ErrorEntity, R>> {

    return flow {

            try {

                val response = execute()

                when (response.isSuccessful) {
                    true -> {
                       
                        emit(Either.Right(transform(response.body()!!)))

                    }

                    false -> {
                       
                        emit(Either.Left(generalErrorImplementation.getHttpErrors(response)))

                    }
                }
            } catch (exception: Throwable) {
               
                emit(Either.Left(generalErrorImplementation.getError(exception)))

            }

    }

}












